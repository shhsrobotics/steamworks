package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.commands.TeleopCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class DriveSubsystem extends Subsystem {

	public static double FOOT_LEFT = -885.5166667;
	public static double FOOT_RIGHT = 192.7833333;
	
	private Talon left_1 = new Talon(RobotMap.LEFT_DRIVE_1);
	private Talon left_2 = new Talon(RobotMap.LEFT_DRIVE_2);
	private Talon right_1 = new Talon(RobotMap.RIGHT_DRIVE_1);
	private Talon right_2 = new Talon(RobotMap.RIGHT_DRIVE_2);
	private RobotDrive drive = new RobotDrive(left_1, left_2, right_1, right_2);
	
	private Encoder leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_PIN_A, RobotMap.LEFT_ENCODER_PIN_B);
	private Encoder rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_PIN_A, RobotMap.RIGHT_ENCODER_PIN_B);

    public void initDefaultCommand() {
    	setDefaultCommand(new TeleopCommand());
    }
    public void drive_joystick(Joystick leftStick, Joystick rightStick) {
    	this.drive.tankDrive(leftStick, rightStick);
    }
    
    public void drive_value(double left_val, double right_val){
    	this.drive.tankDrive(left_val, right_val);
    }
    
    public void slow_drive_joystick(Joystick leftStick, Joystick rightStick){
    	double left_val;
    	double right_val;
    	left_val = leftStick.getY();
    	left_val = slow_cook(left_val);
    	right_val = rightStick.getY();
    	right_val = slow_cook(right_val);
    	this.drive.tankDrive(left_val,  right_val);
    }
    
    public void slowDrive_value(double left_val, double right_val){
    	double right_vale = slow_cook(right_val);
    	double left_vale = slow_cook(left_val);
    	this.drive.tankDrive(left_vale, right_vale);
    }
    
    private double slow_cook(double val_in){
    	double val;
    	val = (val_in * val_in) * 0.5;
    	return val;
    }
    
    public void initdrive() {
    	this.drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
    	this.drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    	this.reset_encoders();
    }
    
    public void reset_encoders(){
    	this.leftEncoder.reset();
    	this.rightEncoder.reset();
    }
    
    public void reset_left_encoder(){
    	this.leftEncoder.reset();
    }
    
    public void reset_right_encoder(){
    	this.rightEncoder.reset();
    }
    
    public double get_left_encoder_raw(){
    	return this.leftEncoder.getRaw();
    }
    
    public double get_left_encoder_rate(){
    	return this.leftEncoder.getRate();
    }
    
    public double get_right_encoder_raw(){
    	return this.rightEncoder.getRaw();
    }
    
    public double get_right_encoder_rate(){
    	return this.rightEncoder.getRate();
    }
    
    public double get_left_encoder_raw_feet(){
    	return this.leftEncoder.getRaw() / DriveSubsystem.FOOT_LEFT;
    }
    
    public double get_left_encoder_rate_feet(){
    	return this.leftEncoder.getRate() / DriveSubsystem.FOOT_LEFT;
    }
    
    public double get_right_encoder_raw_feet(){
    	return this.rightEncoder.getRaw() / DriveSubsystem.FOOT_RIGHT;
    }
    
    public double get_right_encoder_rate_feet(){
    	return this.rightEncoder.getRate() / DriveSubsystem.FOOT_RIGHT;
    }
    
}

