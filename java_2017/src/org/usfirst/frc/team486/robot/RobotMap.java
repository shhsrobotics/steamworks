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
	// OPSTICK
	public static int TRACK_BUTTON = 1;
	public static int GRAB_BUTTON = 2;
	public static int SHOOT_OVERRIDE = 3;
	public static int RESET_BUTTON = 6;
	public static int JOSTLE_BUTTON = 11;
	public static int JOSTLE_BUTTON_2 = 12;
	public static int SHOOT_REG_DEBUG = 4;
	public static int SHOOT_67K_DEBUG = 9;
	public static int SHOOT_54K_DEBUG = 8;
	public static int SHOOT_30K_DEBUG = 7;
	// LEFTSTICK
	// RIGHTSTICK
	public static int WINCH_BUTTON = 1;
	public static int SLOW_BUTTON = 2;
	
	// ----------------------------------------------------------
	// PWM PORTS
	// ----------------------------------------------------------
	public static int LEFT_DRIVE_1 = 0;
	public static int LEFT_DRIVE_2 = 1;
	public static int RIGHT_DRIVE_1 = 2;
	public static int RIGHT_DRIVE_2 = 3;
	public static int SHOOTER_PIN = 4;
	public static int WINCH_PIN = 5;
	
	// ----------------------------------------------------------
	// DIO PORTS
	// ----------------------------------------------------------
	public static int SHOOTER_ENCODER_PIN_A = 0;
	public static int SHOOTER_ENCODER_PIN_B = 1;
	public static int LEFT_ENCODER_PIN_A = 2;
	public static int LEFT_ENCODER_PIN_B = 3;
	public static int RIGHT_ENCODER_PIN_A = 4;
	public static int RIGHT_ENCODER_PIN_B = 5;
	
	// ----------------------------------------------------------
	// RELAYS
	// ----------------------------------------------------------
	public static int LED_PIN = 1;
	
	// ----------------------------------------------------------
	// PNEUMATICS
	// ----------------------------------------------------------
	public static int COMPRESSOR_PIN = 0;
	public static int LIFT_PIN = 0;
	public static int CLAW_PIN = 1;
	public static int SHOOTER_REG_PIN = 2;
	
	// ----------------------------------------------------------
	// CONTROL VALUES
	// ----------------------------------------------------------
	public static double OPSTICK_THRESHOLD = 0.3;
	//-----------------------------------------------------------
	// ANALOG
	//-----------------------------------------------------------
	public static int GYRO_PIN = 0;
}
