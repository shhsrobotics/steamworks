package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.robot.commands.Open;
import org.usfirst.frc.team486.robot.commands.groups.JostleBalls;
import org.usfirst.frc.team486.robot.commands.AutoShootPID;
import org.usfirst.frc.team486.robot.commands.Climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	// ----------------------------------------------------------
	// JOYSTICKS
	// ----------------------------------------------------------
	public Joystick leftstick = new Joystick(RobotMap.LEFT_STICK_PORT);
	public Joystick rightstick = new Joystick(RobotMap.RIGHT_STICK_PORT);
	public Joystick opstick = new Joystick(RobotMap.OP_STICK_PORT);
	public Joystick station = new Joystick(RobotMap.STATION_PORT);
	
	// ----------------------------------------------------------
	// OPSTICK BUTTONS
	// ----------------------------------------------------------
	public JoystickButton ball_in = new JoystickButton(opstick, RobotMap.BALL_IN_BUTTON);
	public JoystickButton jostle = new JoystickButton(opstick, RobotMap.JOSTLE_BUTTON);
	public JoystickButton low_speed = new JoystickButton(opstick, RobotMap.LOW_SPEED_BUTTON);
	public JoystickButton mid_speed = new JoystickButton(opstick, RobotMap.MID_SPEED_BUTTON);
	public JoystickButton high_speed = new JoystickButton(opstick, RobotMap.HIGH_SPEED_BUTTON);
	//public JoystickButton winch = new JoystickButton(opstick, RobotMap.WINCH_BUTTON);
	
	// ----------------------------------------------------------
	// OPSTICK BUTTONS
	// ----------------------------------------------------------
	public JoystickButton top = new JoystickButton(station, RobotMap.TOP_BUTTON);
	public JoystickButton mid = new JoystickButton(station, RobotMap.MID_BUTTON);
	public JoystickButton winch = new JoystickButton(station, RobotMap.BOT_BUTTON);
	
	// ----------------------------------------------------------
	// LEFTSTICK BUTTONS
	// ----------------------------------------------------------
	public JoystickButton left_slow = new JoystickButton(leftstick, RobotMap.SLOW_DRIVE_LEFT);
	public JoystickButton left_lower = new JoystickButton(leftstick, RobotMap.LEFT_LOWER_BUTTON);
	public JoystickButton left_lift = new JoystickButton(leftstick, RobotMap.LEFT_LIFT_BUTTON);
	public JoystickButton left_open = new JoystickButton(leftstick, RobotMap.LEFT_OPEN_BUTTON);
	public JoystickButton left_close = new JoystickButton(leftstick, RobotMap.LEFT_CLOSE_BUTTON);
	
	// ----------------------------------------------------------
	// RIGHTSTICK BUTTONS
	// ----------------------------------------------------------
	public JoystickButton right_slow = new JoystickButton(rightstick, RobotMap.SLOW_DRIVE_RIGHT);
	public JoystickButton right_lower = new JoystickButton(rightstick, RobotMap.RIGHT_LOWER_BUTTON);
	public JoystickButton right_lift = new JoystickButton(rightstick, RobotMap.RIGHT_LIFT_BUTTON);
	public JoystickButton right_open = new JoystickButton(rightstick, RobotMap.RIGHT_OPEN_BUTTON);
	public JoystickButton right_close = new JoystickButton(rightstick, RobotMap.RIGHT_CLOSE_BUTTON);

	// ----------------------------------------------------------
	// OI METHOD FOR BUTTON TRIGGERS
	// ----------------------------------------------------------
	public OI(){
		// ------------------------------------------------------
		// OPSTICK COMMANDS
		// ------------------------------------------------------
		ball_in.whileActive(new Open()); // Retracts piston to let balls into shooter
		jostle.whenActive(new JostleBalls(0.5, 1.0, 1)); // Jostles the robot so that any stuck balls can enter the shooter
		low_speed.whileActive(new AutoShootPID(RobotMap.SHOOTSPEED_1)); // Spin the shooter motors at the low preset speed
		mid_speed.whileActive(new AutoShootPID(RobotMap.SHOOTSPEED_2)); // Spin the shooter motors at the normal preset speed
		high_speed.whileActive(new AutoShootPID(RobotMap.SHOOTSPEED_3)); // Spin the shooter motors at the high preset speed
		winch.whileActive(new Climb()); // Spin the winch to climb the rope
		// Driver stick commands done with triggers, since all are two buttons (and duplicated)
	}
}
