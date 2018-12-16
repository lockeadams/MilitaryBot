import com.fazecast.jSerialComm.*;
import edu.wpi.first.networktables.*;
import java.io.*;

public class Client {
  public static void main(String[] args) throws IOException {
    new Client().run();
  }

  public void run() throws IOException {

    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("datatable");
    NetworkTableEntry xEntry = table.getEntry("x");
    NetworkTableEntry yEntry = table.getEntry("y");
    NetworkTableEntry zEntry = table.getEntry("z");
    inst.startClientTeam(4188);
    inst.startDSClient();

    String data = "";
    SerialPort comPort = SerialPort.getCommPorts()[1];
    comPort.openPort();
    comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
    InputStream in = comPort.getInputStream();
    while (true) {
      char key;
      try {
        key = (char) in.read();
      } catch (Exception e) {
        System.err.println("input stream not read");
        try {
          Thread.sleep(500);
        } catch (InterruptedException ee) {
          ee.printStackTrace();
        }
        key = (char) in.read();
      }

      String keyString = Character.toString(key);
      if (key == '>' && data.contains("/") && data.length() > 5 && data.contains("<")) {
        data = data.concat(keyString);
        System.out.println(data);

        int ind1 = data.indexOf('/', 0);
        int ind2 = data.indexOf('/', ind1 + 1);
        int ind3 = data.indexOf('>');
        int startind = data.indexOf('<');

        // System.out.println("start ind: " + startind + " ind1: " + ind1 + " ind2: " + ind2 + " ind3: " + ind3);

        int xVal = Integer.parseInt(data.substring(startind + 1, ind1));
        int yVal = Integer.parseInt(data.substring(ind1 + 1, ind2));
        int zVal = Integer.parseInt(data.substring(ind2 + 1, ind3));

        xEntry.setDouble(xVal);
        yEntry.setDouble(yVal);
        zEntry.setDouble(zVal);

        data = "";
      } else if (key == '>' && !(data.contains("<"))) {
        data = "";
      } else {
        data = data.concat(keyString);
      }
    }
  }
}