package org.usfirst.frc7913.Main;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class IO {

    public JoystickButton button1;
    public JoystickButton button2;
    public JoystickButton button3;
    public JoystickButton button4;
    public JoystickButton button5;
    public JoystickButton button6;
    public JoystickButton button7;
    // public Joystick joystickx30;
    public XboxController xboxController;
    boolean status = true;

    public IO() {
        xboxController = new XboxController(0);
        // joystickx30 = new Joystick(0); 
    }

    // public Joystick getjoystickx30() {
    //     return joystickx30;
    // }
    public XboxController getXboxController() {
        return xboxController;
    }
}