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
	public static int TRACK_BUTTON_PORT = 1;
	
	// ----------------------------------------------------------
	// PWM PORTS
	// ----------------------------------------------------------
	public static int LEFT_DRIVE_1 = 0;
	public static int LEFT_DRIVE_2 = 1;
	public static int RIGHT_DRIVE_1 = 2;
	public static int RIGHT_DRIVE_2 = 3;
	
	// ----------------------------------------------------------
	// RELAYS
	// ----------------------------------------------------------
	public static int led_pin = 1;
}
