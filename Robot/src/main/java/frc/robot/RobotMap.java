/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class RobotMap {

  public static WPI_TalonSRX frontLeft, rearLeft, frontRight, rearRight;
  public static double MAX_VELOCITY = 1000;

  public void init() {

    frontLeft = new WPI_TalonSRX(5);
    rearLeft = new WPI_TalonSRX(6);
    frontRight = new WPI_TalonSRX(8);
    rearRight = new WPI_TalonSRX(7);

    rearLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative,0, 0);
		rearLeft.setSensorPhase(true);
		rearRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rearRight.setSensorPhase(true);

    frontLeft.follow(rearLeft);
    frontRight.follow(rearRight);

  }

}
