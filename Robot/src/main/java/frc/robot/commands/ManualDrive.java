/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ManualDrive extends Command {

  public enum Loop {CLOSED, OPEN};
  Loop type;

  public ManualDrive(Loop type) {
    this.type = type;
    requires(Robot.m_drivetrain);
  }

  @Override
  protected void initialize() {
    System.out.println("Manual drive.");
  }

  @Override
  protected void execute() {

    double xSpeed = Robot.m_oi.pilot.getY(Hand.kLeft);
	  double zTurn = Robot.m_oi.pilot.getX(Hand.kRight);

    if(type == Loop.CLOSED){
      Robot.m_drivetrain.velocityDrive(xSpeed, zTurn, RobotMap.MAX_VELOCITY);
    } else if(type == Loop.OPEN) {
      RobotMap.drive.arcadeDrive(xSpeed, zTurn);
    }
    
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