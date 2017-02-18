package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.robot.commands.GrabGear;
import org.usfirst.frc.team486.robot.commands.Open;
import org.usfirst.frc.team486.robot.commands.Turn;
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
	
	// ----------------------------------------------------------
	// JOYSTICK BUTTONS
	// ----------------------------------------------------------
	public JoystickButton trackbutton = new JoystickButton(opstick, RobotMap.TRACK_BUTTON);
	public JoystickButton grabbutton = new JoystickButton(opstick, RobotMap.GRAB_BUTTON);
	public JoystickButton shootoveride = new JoystickButton(opstick, RobotMap.SHOOT_OVERRIDE);
	public JoystickButton winchbutton = new JoystickButton(rightstick, RobotMap.WINCH_BUTTON);
	public JoystickButton slowbutton = new JoystickButton(rightstick, RobotMap.SLOW_BUTTON);
	
	public JoystickButton jostlebutton = new JoystickButton(opstick, RobotMap.JOSTLE_BUTTON);
	public JoystickButton jostlebutton2 = new JoystickButton(opstick, RobotMap.JOSTLE_BUTTON_2);
	
	// ----------------------------------------------------------
	// DEBUG BUTTONS
	// ----------------------------------------------------------
	public JoystickButton shootregdebug = new JoystickButton(opstick, RobotMap.SHOOT_REG_DEBUG);
	public JoystickButton shootdebug30k = new JoystickButton(opstick, RobotMap.SHOOT_30K_DEBUG);
	public JoystickButton shootdebug54k = new JoystickButton(opstick, RobotMap.SHOOT_54K_DEBUG);
	public JoystickButton shootdebug67k = new JoystickButton(opstick, RobotMap.SHOOT_67K_DEBUG);
	public JoystickButton resetbutton = new JoystickButton(opstick, RobotMap.RESET_BUTTON); 

	// ----------------------------------------------------------
	// OI METHOD FOR BUTTON TRIGGERS
	// ----------------------------------------------------------
	public OI(){
		grabbutton.whileActive(new GrabGear());
		//shootregdebug.whenActive(new AcceptBall_DEBUG(true));
		//shootregdebug.whenInactive(new AcceptBall_DEBUG(false));
		//trackbutton.whileHeld(new ShooterCommand());
		//trackbutton.whenActive(new ShootAuto());
		shootregdebug.whileActive(new Open());
		shootdebug30k.whenActive(new AutoShootPID(30000.0));
		shootdebug54k.whenActive(new AutoShootPID(54000.0));
		shootdebug67k.whenActive(new AutoShootPID(67000.0));
		winchbutton.whileActive(new Climb());
		jostlebutton.whenActive(new JostleBalls(0.5, 1.0, 1));
		jostlebutton2.whenActive(new JostleBalls(1.0, 1.0, 1));
	}
}
