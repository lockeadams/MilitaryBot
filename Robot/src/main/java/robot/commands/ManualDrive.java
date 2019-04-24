package robot.commands;

import robot.OI;
import robot.Robot;
import robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/** Controls drivetrain using split arcade drive. */
public class ManualDrive extends Command {

    OI oi = Robot.oi;
    Drivetrain drivetrain = Robot.drivetrain;

    public ManualDrive() {
        requires(drivetrain);
    }

    @Override
    protected void initialize() {
        System.out.println("Manual Drive");
    }

    @Override
    protected void execute() {
        double xSpeed = oi.pilot.getY(Hand.kLeft);
        double zTurn = oi.pilot.getX(Hand.kRight);
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
