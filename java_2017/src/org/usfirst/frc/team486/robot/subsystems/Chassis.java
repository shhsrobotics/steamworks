package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.commands.Teleop;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Chassis extends Subsystem {

	private Talon left_1 = new Talon(RobotMap.LEFT_DRIVE_1);
	private Talon left_2 = new Talon(RobotMap.LEFT_DRIVE_2);
	private Talon right_1 = new Talon(RobotMap.RIGHT_DRIVE_1);
	private Talon right_2 = new Talon(RobotMap.RIGHT_DRIVE_2);
	private RobotDrive drive = new RobotDrive(left_1, left_2, right_1, right_2);
	
	private Encoder leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_PIN_A, RobotMap.LEFT_ENCODER_PIN_B);
	private Encoder rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_PIN_A, RobotMap.RIGHT_ENCODER_PIN_B);
	
	private AnalogGyro gyro = new AnalogGyro(RobotMap.GYRO_PIN);
	double Kp = 0.3;

    public void initDefaultCommand() {
    	setDefaultCommand(new Teleop());
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
    	this.drive.tankDrive(right_val, left_val);
    }
    
    public void slowDrive_value(double left_val_in, double right_val_in){
    	double right_val = slow_cook(right_val_in);
    	double left_val = slow_cook(left_val_in);
    	this.drive.tankDrive(right_val, left_val);
    }
    
    private double slow_cook(double val_in){
    	double val;
    	val = (val_in)*0.5;
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
    
    public void gyro_start(){
    	gyro.calibrate();
    }
    
    public double gyro_angle(){
    	return gyro.getAngle();
    }
    
    public void gyro_reset(){
    	gyro.reset();
    }
}
