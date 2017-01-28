package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.robot.commands.GearLiftCommand;
import org.usfirst.frc.team486.robot.triggers.OpstickBackTrigger;
import org.usfirst.frc.team486.robot.triggers.OpstickForwardTrigger;

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
	public JoystickButton trackbutton = new JoystickButton(opstick, RobotMap.TRACK_BUTTON_PORT);
	
	// ----------------------------------------------------------
	// TRIGGERS
	// ----------------------------------------------------------
	public OpstickForwardTrigger opstickforward = new OpstickForwardTrigger();
	public OpstickBackTrigger opstickback = new OpstickBackTrigger();

	// ----------------------------------------------------------
	// OI METHOD FOR BUTTON TRIGGERS
	// ----------------------------------------------------------
	public OI(){
		opstickforward.whileActive(new GearLiftCommand(true));
		opstickback.whenActive(new GearLiftCommand(false));
	}
}
