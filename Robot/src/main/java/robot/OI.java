package robot;

import robot.commands.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    public XboxController pilot = new XboxController(0);
    JoystickButton a = new JoystickButton(pilot, 1);
    JoystickButton b = new JoystickButton(pilot, 2);

    public OI() {
        a.toggleWhenPressed(new PowerGloveDrive());
        b.whenPressed(new ManualDrive());
    }

}
