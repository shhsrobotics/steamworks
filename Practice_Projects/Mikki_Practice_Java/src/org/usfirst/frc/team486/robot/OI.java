package org.usfirst.frc.team486.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team486.robot.commands.LEDCommand;
import org.usfirst.frc.team486.robot.commands.RollerCommand;
import org.usfirst.frc.team486.robot.commands.SolenoidCommand;
import org.usfirst.frc.team486.robot.commands.SolenoidSlidesCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	public Joystick leftstick = new Joystick(0);
	public Joystick rightstick = new Joystick(1);
	public Joystick opstick = new Joystick(2);
	public Button ledbutton = new JoystickButton(opstick, RobotMap.LED_BUTTON);
	public Button rollerbutton = new JoystickButton(opstick, RobotMap.ROLLER_BUTTON);
	public Button solenoidopenbutton = new JoystickButton(opstick, RobotMap.SOLENOIDOPEN_BUTTON);
	public Button solenoidclosebutton = new JoystickButton(opstick, RobotMap.SOLENOIDCLOSE_BUTTON);
	public Button solenoidslidebutton = new JoystickButton(opstick, RobotMap.SOLENOIDSLIDE_BUTTON);
	
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public OI() {
		ledbutton.whileHeld(new LEDCommand());
		rollerbutton.whileHeld(new RollerCommand());
		solenoidopenbutton.whileHeld(new SolenoidCommand(true));
		solenoidclosebutton.whileHeld(new SolenoidCommand(false));
		solenoidslidebutton.whileHeld(new SolenoidSlidesCommand());
	}
}