package robot.subsystems;

import robot.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Drivetrain extends Subsystem {

    private WPI_TalonSRX leftMaster = new WPI_TalonSRX(8);
    private WPI_TalonSRX leftSlave = new WPI_TalonSRX(7);
    private WPI_TalonSRX rightMaster = new WPI_TalonSRX(9);
    private WPI_TalonSRX rightSlave = new WPI_TalonSRX(10);
    private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);

    public Drivetrain() {
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualDrive());
    }

    /** Controls drivetrain using arcade steering model. */
    public void arcade(double xSpeed, double zTurn) {
        drive.arcadeDrive(xSpeed, zTurn);
    }

}
