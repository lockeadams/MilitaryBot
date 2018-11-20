/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.PowerGloveDrive;

public class OI {
  public XboxController pilot;
  JoystickButton a;

  public OI() {
    pilot = new XboxController(0);

    a = new JoystickButton(pilot, 1);

    a.toggleWhenPressed(new PowerGloveDrive());
  }
}
