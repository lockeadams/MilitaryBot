import com.fazecast.jSerialComm.*;
import edu.wpi.first.networktables.*;
import java.io.*;

public class Client {
  
    public static void main(String[] args) throws IOException {
        new Client().run();
    }

    public void run() throws IOException {

        //create network table and declare entries for x, y, z, then start client
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable("datatable");
        NetworkTableEntry xEntry = table.getEntry("x");
        NetworkTableEntry yEntry = table.getEntry("y");
        NetworkTableEntry zEntry = table.getEntry("z");
        inst.startClientTeam(4188);
        inst.startDSClient();

        //open and configure serial port and input stream
        String data = "";
        SerialPort comPort = SerialPort.getCommPort("COM5");
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
        InputStream in = comPort.getInputStream();

        int slashCount = 0;

        //constantly read input stream
        while (true) {
        
            //attempt to get first character from stream
            char key;
            try {
                key = (char) in.read();
            } catch (Exception e) {
                //if cannot read stream, wait .5 s and try again
                System.err.println("input stream not read");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ee) {
                    ee.printStackTrace();
                }
                key = (char) in.read();
            }

            String keyString = Character.toString(key);

            //construct string from incoming characters if protocol is complete
            if (key == '>' && data.contains("/") && 5 < data.length() && data.length() < 17 && data.contains("<") && slashCount == 2) {
                data = data.concat(keyString);
                System.out.println(data);

                int ind1 = data.indexOf('/', 0);
                int ind2 = data.indexOf('/', ind1 + 1);
                int ind3 = data.indexOf('>');
                int startind = data.indexOf('<');

                //seperate string based on protocol into 3 values
                int xVal = Integer.parseInt(data.substring(startind + 1, ind1));
                int yVal = Integer.parseInt(data.substring(ind1 + 1, ind2));
                int zVal = Integer.parseInt(data.substring(ind2 + 1, ind3));

                //set entry in network table
                xEntry.setDouble(xVal);
                yEntry.setDouble(yVal);
                zEntry.setDouble(zVal);

                //reset data string
                data = "";
                slashCount = 0;

            //if string has end character but no start, reset string and try again
            } else if (key == '>' && !(data.contains("<"))) {
                data = "";
                slashCount = 0;
            //otherwise just add character to current string
            } else {
                data = data.concat(keyString);
                if(keyString.equalsIgnoreCase("/")) slashCount++;
            }
        }
    }
}
