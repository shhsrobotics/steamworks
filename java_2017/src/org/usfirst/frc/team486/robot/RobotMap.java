package org.usfirst.frc.team486.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// ----------------------------------------------------------
	// USB PORTS
	// ----------------------------------------------------------
	public static int LEFT_STICK_PORT = 0;
	public static int RIGHT_STICK_PORT = 1;
	public static int OP_STICK_PORT = 2;
	
	// ----------------------------------------------------------
	// BUTTON NUMBERS
	// ----------------------------------------------------------
	public static int TRACK_BUTTON = 1;
	public static int GRAB_BUTTON = 2;
	public static int SHOOT_BUTTON = 3;
	
	// ----------------------------------------------------------
	// PWM PORTS
	// ----------------------------------------------------------
	public static int LEFT_DRIVE_1 = 0;
	public static int LEFT_DRIVE_2 = 1;
	public static int RIGHT_DRIVE_1 = 2;
	public static int RIGHT_DRIVE_2 = 3;
	public static int SHOOTER_PIN = 4;
	
	// ----------------------------------------------------------
	// RELAYS
	// ----------------------------------------------------------
	public static int LED_PIN = 1;
	
	// ----------------------------------------------------------
	// PNEUMATICS
	// ----------------------------------------------------------
	public static int COMPRESSOR_PIN = 0;
	public static int LIFT_PIN = 0;
	public static int GRAB_PIN = 1;
	public static int SHOOTER_REG_PIN = 2;
	
	// ----------------------------------------------------------
	// CONTROL VALUES
	// ----------------------------------------------------------
	public static double OPSTICK_THRESHOLD = 0.3;
}
