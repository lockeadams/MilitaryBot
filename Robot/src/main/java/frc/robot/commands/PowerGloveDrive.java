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
import frc.robot.subsystems.Drivetrain.Loop;

public class PowerGloveDrive extends Command {

  NetworkTableInstance inst;
  NetworkTable table;
  final double MAX_X, MAX_Y, X_DEAD_MIN, X_DEAD_MAX, Y_DEAD_MIN, Y_DEAD_MAX;
  double x, y;
  Loop type;

  public PowerGloveDrive(Loop type) {
    requires(Robot.m_drivetrain);
    MAX_X = 150;
    MAX_Y = 215;
    X_DEAD_MIN = -75;
    X_DEAD_MAX = 90;
    Y_DEAD_MIN = -60;
    Y_DEAD_MAX = 60;
    this.type = type;
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

    if(type == Loop.OPEN) RobotMap.drive.arcadeDrive(xSpeed, zTurn);
    if(type == Loop.CLOSED) Robot.m_drivetrain.velocityDrive(xSpeed, zTurn, RobotMap.MAX_VELOCITY);
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
