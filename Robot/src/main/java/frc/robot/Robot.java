/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;

public class Robot extends TimedRobot {
  public static Drivetrain m_drivetrain;
  public static OI m_oi;

  @Override
  public void robotInit() {
    RobotMap.init();
    m_drivetrain = new Drivetrain();
    m_oi = new OI();  
    CameraServer.getInstance().startAutomaticCapture();  
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("L Vel", RobotMap.rearLeft.getSelectedSensorVelocity(0));
    SmartDashboard.putNumber("R Vel", RobotMap.rearRight.getSelectedSensorVelocity(0));
  }

  @Override
  public void testPeriodic() {
  }
}
