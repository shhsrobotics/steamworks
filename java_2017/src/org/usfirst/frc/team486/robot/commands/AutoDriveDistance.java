package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveDistance extends Command {
	
	private double feet, speed;
	private double lMod = 1.0;
	private double rMod = 1.0;

    public AutoDriveDistance(double feet, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivechain);
    	this.feet = feet;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivechain.reset_encoders();
    	Robot.drivechain.drive_value(speed, speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.drivechain.get_left_encoder_rate_feet() < Robot.drivechain.get_right_encoder_rate_feet()) {
    		rMod = Robot.drivechain.get_left_encoder_rate_feet() * rMod / Robot.drivechain.get_right_encoder_rate_feet() * lMod;
    	} else if (Robot.drivechain.get_left_encoder_rate_feet() > Robot.drivechain.get_right_encoder_rate_feet()) {
    		lMod = Robot.drivechain.get_right_encoder_rate_feet() * lMod / Robot.drivechain.get_left_encoder_rate_feet() * rMod;
    	}
    	if (rMod < 1.0 && lMod < 1.0) {
    		if (rMod > lMod) {
    			double scale = 1.0/rMod;
    			rMod = 1.0;
    			lMod = lMod * scale;
    		} else {
    			double scale = 1.0/lMod;
    			lMod = 1.0;
    			rMod = rMod * scale;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.drivechain.get_left_encoder_raw_feet() >= feet && Robot.drivechain.get_right_encoder_raw_feet() >= feet);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivechain.drive_value(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
