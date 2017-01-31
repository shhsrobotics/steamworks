package org.usfirst.frc.team486.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	public static int LEFT_STICK_PIN = 0;
	public static int RIGHT_STICK_PIN = 1;
	public static int OPSTICK_PIN = 2;
	
	public static int SHOOTER_BUTTON = 1;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static int Left_Drive1 = 0;
	public static int Left_Drive2 = 1;
	public static int Right_Drive1 = 2;
	public static int Right_Drive2 = 3;
	public static int SHOOTER_PIN = 4;
}
