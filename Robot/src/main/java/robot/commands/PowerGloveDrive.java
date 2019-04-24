package robot.commands;

import robot.Robot;
import robot.subsystems.Drivetrain;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Command;

/** Controls drivetrain based on glove input. */
public class PowerGloveDrive extends Command {

    final double MAX_X = 150;
    final double MAX_Y = 215;
    final double X_DEAD_MIN = -75;
    final double X_DEAD_MAX = 90;
    final double Y_DEAD_MIN = -60;
    final double Y_DEAD_MAX = 60;

    Drivetrain drivetrain = Robot.drivetrain;
    NetworkTableInstance inst;
    NetworkTable table;

    public PowerGloveDrive() {
        requires(drivetrain);
    }

    @Override
    protected void initialize() {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("datatable");
    }

    @Override
    protected void execute() {

        double xEntry = table.getEntry("x").getDouble(0);
        double yEntry = table.getEntry("y").getDouble(0);

        double x, y;
        if(xEntry > X_DEAD_MIN && xEntry < X_DEAD_MAX) {
          x = 0;
        } else if(xEntry > MAX_X) {
          x = MAX_X;
        } else {
          x = xEntry;
        }

        if(yEntry > Y_DEAD_MIN && yEntry < Y_DEAD_MAX) {
          y = 0;
        } else if(yEntry > MAX_Y) {
          y = MAX_Y;
        } else {
          y = yEntry;
        }

        System.out.println("x: " + x + " y: " + y);

        double xSpeed = -y / MAX_Y;
        double zTurn = x / MAX_X;

        drivetrain.arcade(xSpeed, zTurn);

  }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
