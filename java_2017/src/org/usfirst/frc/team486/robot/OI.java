package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.robot.commands.GearGrabCommand;
import org.usfirst.frc.team486.robot.commands.ShooterCommand;
import org.usfirst.frc.team486.robot.commands.ShooterRegDebugCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	// ----------------------------------------------------------
	// JOYSTICKS
	// ----------------------------------------------------------
	public Joystick leftstick = new Joystick(RobotMap.LEFT_STICK_PORT);
	public Joystick rightstick = new Joystick(RobotMap.RIGHT_STICK_PORT);
	public Joystick opstick = new Joystick(RobotMap.OP_STICK_PORT);
	
	// ----------------------------------------------------------
	// JOYSTICK BUTTONS
	// ----------------------------------------------------------
	public JoystickButton trackbutton = new JoystickButton(opstick, RobotMap.TRACK_BUTTON);
	public JoystickButton grabbutton = new JoystickButton(opstick, RobotMap.GRAB_BUTTON);
	public JoystickButton shootoveride = new JoystickButton(opstick, RobotMap.SHOOT_OVERRIDE);
	public JoystickButton shootregdebug = new JoystickButton(opstick, RobotMap.SHOOT_REG_DEBUG);

	// ----------------------------------------------------------
	// OI METHOD FOR BUTTON TRIGGERS
	// ----------------------------------------------------------
	public OI(){
		grabbutton.whileActive(new GearGrabCommand());
		shootregdebug.whenActive(new ShooterRegDebugCommand(true));
		shootregdebug.whenInactive(new ShooterRegDebugCommand(false));
		//trackbutton.whileHeld(new ShooterCommand());
	}
}
