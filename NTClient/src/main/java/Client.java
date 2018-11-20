import java.io.*;
import com.fazecast.jSerialComm.*;
import edu.wpi.first.networktables.*;

public class Client {
    public static void main(String[] args) {
        new Client().run();
    }

  public void run() {
    SerialPort comPort = SerialPort.getCommPorts()[0];
    comPort.openPort();
    
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("datatable");
    NetworkTableEntry xEntry = table.getEntry("x");
    NetworkTableEntry yEntry = table.getEntry("y");
    NetworkTableEntry zEntry = table.getEntry("z");
    inst.startClientTeam(4188);
    inst.startDSClient();
    while (true) {
      
        try {
        Thread.sleep(20);
      } catch (InterruptedException ex) {
        System.out.println("interrupted");
        return;
      }

      // TODO: change values based on incoming serial data
      double xVal = 1;
      double yVal = 1;
      double zVal = 1;
      xEntry.setDouble(xVal);
      yEntry.setDouble(yVal);
      zEntry.setDouble(zVal);
    }
  }
}