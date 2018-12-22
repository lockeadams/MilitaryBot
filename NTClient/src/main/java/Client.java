import com.fazecast.jSerialComm.*;
import edu.wpi.first.networktables.*;
import java.io.*;
import java.util.Scanner;

public class Client {
  
    public static void main(String[] args) throws IOException {
        new Client().run();
    }

    public void run() throws IOException {

        //ask for com port
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter com port that device is connected to.");
        System.out.println("e.g. COM3 or COM5");
        String com = scanner.nextLine();
        scanner.close();

        //open and configure serial port and input stream
        String data = "";
        System.out.println("Connecting to " + com);
        SerialPort comPort = SerialPort.getCommPort(com);
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
        InputStream in = comPort.getInputStream();
        
        //create network table and declare entries for x, y, z, then start client
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable("datatable");
        NetworkTableEntry xEntry = table.getEntry("x");
        NetworkTableEntry yEntry = table.getEntry("y");
        NetworkTableEntry zEntry = table.getEntry("z");
        inst.startClientTeam(4188);

        int slashCount = 0;

        //constantly read input stream
        while (true) {
        
            //attempt to get characters from stream
            char key;
            if(in != null) {
                key = (char) in.read();
                String keyString = Character.toString(key);

                //construct string from incoming characters if protocol is complete
                if (key == '>' && data.contains("/") && 5 < data.length() && data.length() < 17 && data.contains("<") && slashCount == 2) {
                    data = data.concat(keyString);
                    System.out.println(data);

                    //get indexes to split data into 3 values
                    int ind1 = data.indexOf('/', 0);
                    int ind2 = data.indexOf('/', ind1 + 1);
                    int ind3 = data.indexOf('>');
                    int startind = data.indexOf('<');

                    //use indexes to split data
                    String xString = data.substring(startind + 1, ind1);
                    String yString = data.substring(ind1 + 1, ind2);
                    String zString = data.substring(ind2 + 1, ind3);

                    //parse strings as ints
                    int xVal = extractDigits(xString);
                    int yVal = extractDigits(yString);
                    int zVal = extractDigits(zString);

                    //set entry in network table
                    xEntry.setDouble(xVal);
                    yEntry.setDouble(yVal);
                    zEntry.setDouble(zVal);

                    //reset data strings
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
            } else {
                System.err.println("Could not read input stream.");
                System.err.println("Ensure glove is connected and com port is correct.");
                try {Thread.sleep(5000);} 
                catch (InterruptedException e) {e.printStackTrace();}
            }
        }
    }

    public int extractDigits(String src) {
        StringBuilder builder = new StringBuilder();
        boolean neg = false;
        if(src.charAt(0) == '-') neg = true;
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (Character.isDigit(c)) builder.append(c);
        }
        int result = Integer.parseInt(builder.toString());
        int output = (neg) ? -result : result;
        return output;
    }
}
