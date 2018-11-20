/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ManualDrive;

public class Drivetrain extends Subsystem {

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualDrive());
  }


  /**
   * xSpeed and zTurn must be double values from -1.0 (full speed reverse) to 1.0 (full speed ahead).
   * MAX_VELOCITY must be given in native talon units.
   * @param xSpeed
   * @param zTurn
   * @param MAX_VELOCITY
   */
  public void velocityDrive(double xSpeed, double zTurn, final double MAX_VELOCITY) {
		double turnRatio;
		double leftInput = xSpeed - zTurn;
		double rightInput = xSpeed + zTurn;
		if(leftInput > MAX_VELOCITY || rightInput > MAX_VELOCITY) {
			if(rightInput > leftInput) {
				turnRatio = leftInput / rightInput;
				rightInput = MAX_VELOCITY;
				leftInput = MAX_VELOCITY * turnRatio;
			} else if (leftInput > rightInput) {
				turnRatio = rightInput / leftInput;
				leftInput = MAX_VELOCITY;
				rightInput = MAX_VELOCITY * turnRatio;
			}
		} else if(leftInput < -MAX_VELOCITY || rightInput < -MAX_VELOCITY) {
			if(rightInput < leftInput) {
				turnRatio = leftInput / rightInput;
				rightInput = -MAX_VELOCITY;
				leftInput = -MAX_VELOCITY * turnRatio;
			} else if (leftInput < rightInput) {
				turnRatio = rightInput / leftInput;
				leftInput = -MAX_VELOCITY;
				rightInput = -MAX_VELOCITY * turnRatio;
			}
		}
		RobotMap.rearRight.set(ControlMode.Velocity, rightInput);
		RobotMap.rearLeft.set(ControlMode.Velocity, leftInput);
		System.out.println("right: " + rightInput + " left: " + leftInput); 
  }

  
}
