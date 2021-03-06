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
	public static int BALL_IN_BUTTON= 1;
	public static int JOSTLE_BUTTON = 2;
	public static int LOW_SPEED_BUTTON = 4;
	public static int MID_SPEED_BUTTON = 3;
	public static int HIGH_SPEED_BUTTON = 5;
	public static int WINCH_BUTTON = 6;
	public static int RPi_DEBUG_BUTTON = 7;
	// LEFTSTICK
	public static int LEFT_LIFT_BUTTON = 3;
	public static int LEFT_LOWER_BUTTON = 2;
	public static int LEFT_CLOSE_BUTTON = 5;
	public static int LEFT_OPEN_BUTTON = 4;
	public static int SLOW_DRIVE_LEFT = 1;
	public static int LEFT_6 = 6;
	public static int LEFT_7 = 7;
	public static int LEFT_8 = 8;
	public static int LEFT_9 = 9;
	public static int LEFT_10 = 10;
	public static int LEFT_11 = 11;
	// RIGHTSTICK
	public static int RIGHT_LIFT_BUTTON = 3;
	public static int RIGHT_LOWER_BUTTON = 2;
	public static int RIGHT_CLOSE_BUTTON = 4;
	public static int RIGHT_OPEN_BUTTON = 5;
	public static int SLOW_DRIVE_RIGHT = 1;
	public static int RIGHT_6 = 6;
	public static int RIGHT_7 = 7;
	public static int RIGHT_8 = 8;
	public static int RIGHT_9 = 9;
	public static int RIGHT_10 = 10;
	public static int RIGHT_11 = 11;
	
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
	public static int COUNTER_PIN = 9;
	
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
	public static double SHOOTSPEED_1 = 50000.0;
	public static double SHOOTSPEED_2 = 54000.0;
	public static double SHOOTSPEED_3 = 64000.0;
	public static double AUTO_MOVE_SPEED = 0.6;
	public static double AUTO_MOVE_SLOW_SPEED = 0.52;
	public static double TURN_SUPER_SLOW_SPEED = 0.45;
	public static double TURN_SLOW_SPEED = 0.52;
	public static double TURN_MED_SPEED = 0.54;
	public static double TURN_FAST_SPEED = 0.56;
	
	//-----------------------------------------------------------
	// ANALOG
	//-----------------------------------------------------------
	public static int GYRO_PIN = 0;
}
