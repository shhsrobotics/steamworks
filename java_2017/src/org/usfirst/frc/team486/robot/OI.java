package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.robot.commands.GrabGear;
import org.usfirst.frc.team486.robot.commands.AcceptBall_DEBUG;
import org.usfirst.frc.team486.robot.commands.AutoShoot;
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
	public JoystickButton resetbutton = new JoystickButton(opstick, RobotMap.RESET_BUTTON);
	
	// ----------------------------------------------------------
	// DEBUG BUTTONS
	// ----------------------------------------------------------
	public JoystickButton shootregdebug = new JoystickButton(opstick, RobotMap.SHOOT_REG_DEBUG);
	public JoystickButton shootdebug30k = new JoystickButton(opstick, RobotMap.SHOOT_30K_DEBUG);
	public JoystickButton shootdebug54k = new JoystickButton(opstick, RobotMap.SHOOT_54K_DEBUG);
	public JoystickButton shootdebug67k = new JoystickButton(opstick, RobotMap.SHOOT_67K_DEBUG);

	// ----------------------------------------------------------
	// OI METHOD FOR BUTTON TRIGGERS
	// ----------------------------------------------------------
	public OI(){
		grabbutton.whileActive(new GrabGear());
		shootregdebug.whenActive(new AcceptBall_DEBUG(true));
		shootregdebug.whenInactive(new AcceptBall_DEBUG(false));
		//trackbutton.whileHeld(new ShooterCommand());
		//trackbutton.whenActive(new ShootAuto());
		shootdebug30k.whenActive(new AutoShoot(30000));
		shootdebug54k.whenActive(new AutoShoot(54000));
		shootdebug67k.whenActive(new AutoShoot(67000));
		winchbutton.whileActive(new Climb());
	}
}
