/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class PowerGloveDrive extends Command {

  NetworkTableInstance inst;
  NetworkTable table;
  final double MAX_X, MAX_Y, MAX_Z;
  

  public PowerGloveDrive() {
    requires(Robot.m_drivetrain);
    MAX_X = 300;
    MAX_Y = 300;
    MAX_Z = 300;
  }

  @Override
  protected void initialize() {
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("datatable");
  }

  @Override
  protected void execute() {
    double x = table.getEntry("x").getDouble(0);
    double y = table.getEntry("y").getDouble(0);
    double z = table.getEntry("z").getDouble(0);


    double xSpeed = x / MAX_X;
    double zTurn = z / MAX_Z;

    Robot.m_drivetrain.velocityDrive(xSpeed, zTurn, RobotMap.MAX_VELOCITY);
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
