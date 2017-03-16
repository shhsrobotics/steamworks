package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {

	private double angle;
	private double speed;
	private double threshold = 3;
	
    public Turn(double angle_in, double speed_in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.angle = angle_in;
    	this.speed = speed_in;
    	DriverStation.reportWarning("New Instance of Turn Command Created", true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.gyro_reset();
    	DriverStation.reportWarning("Reset Gyro in Turn Command", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (angle < 0){
    		// Right
    		Robot.chassis.drive_value(this.speed, -this.speed);
    		DriverStation.reportWarning("Turning Right", true);
    	} else {
    		// Left
    		Robot.chassis.drive_value(-this.speed, this.speed);
    		DriverStation.reportWarning("Turning Left", true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double offset = Math.abs(this.angle - Robot.chassis.gyro_angle());
        return (offset < threshold);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.drive_value(0, 0);
    	DriverStation.reportWarning("Done Turning", true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassis.drive_value(0, 0);
    	DriverStation.reportWarning("Done Turning", true);
    }
}
