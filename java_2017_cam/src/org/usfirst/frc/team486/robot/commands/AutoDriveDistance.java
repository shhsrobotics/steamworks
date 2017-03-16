package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveDistance extends Command {
	
	private double inches, speed;
	private double x0_left = 0;
	private double x0_right = 0;
	private Timer time = new Timer();
	private double limit = 2;

    public AutoDriveDistance(double inches_in, double speed_in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	this.inches = inches_in;
    	this.speed = speed_in;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.reset_encoders();
    	Robot.chassis.drive_value(speed, speed);
    	DriverStation.reportWarning("Starting drive command: speed " + speed + ", distance " + inches, true);
    	Robot.chassis.gyro_reset();
    	this.time.start();
    }
    
    protected void execute() {
    	Robot.chassis.drive_value(speed, speed);
    }
    
    protected boolean isFinished() {
    	double x_left = Robot.chassis.get_left_encoder_raw_inches();
    	double x_right = Robot.chassis.get_right_encoder_raw_inches();
    	boolean stalled = false;
    	boolean done = false;
    	if (this.time.get() >= this.limit){
    		this.time.reset();
    		if ((Math.abs(x_left - this.x0_left) <= Math.abs(Robot.chassis.INCH_LEFT)) |
    			(Math.abs(x_right - this.x0_right) <= Math.abs(Robot.chassis.INCH_RIGHT))){
    			stalled = true;
    		} else {
    			this.x0_left = x_left;
    			this.x0_right = x_right;
    		}
    	}
    	if (inches >= 0) {
    		done = (Robot.chassis.get_left_encoder_raw_inches() > inches);
    	} else {
    		done = (Robot.chassis.get_left_encoder_raw_inches() < inches);
    	}
    	return (done | stalled);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.drive_value(0.0, 0.0);
    	DriverStation.reportWarning("Finishing drive command: speed " + speed + ", distance " + inches, true);
    	DriverStation.reportWarning("Final gyro value: " + Robot.chassis.gyro_angle(), true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassis.drive_value(0.0, 0.0);
    	DriverStation.reportWarning("Interrupting drive command: speed " + speed + ", distance " + inches, true);
    }
}
