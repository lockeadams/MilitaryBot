/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {

  public static WPI_TalonSRX frontLeft, rearLeft, frontRight, rearRight;
  public static double MAX_VELOCITY = 2400;
  public static DifferentialDrive drive;

  public static void init() {

    frontLeft = new WPI_TalonSRX(8);
    rearLeft = new WPI_TalonSRX(7);
    frontRight = new WPI_TalonSRX(9);
    rearRight = new WPI_TalonSRX(10);

    rearLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative,0, 0);
    rearLeft.setSensorPhase(false);
    rearLeft.configOpenloopRamp(0.3, 10);

		rearRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    rearRight.setSensorPhase(true);
    rearRight.configOpenloopRamp(0.3, 10);

    frontLeft.follow(rearLeft);
    frontRight.follow(rearRight);

    drive = new DifferentialDrive(rearLeft, rearRight);
  }

}
