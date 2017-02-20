package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveDistance extends Command {
	
	private double inches, speed;

    public AutoDriveDistance(double inches_in, double speed_in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivechain);
    	this.inches = inches_in;
    	this.speed = speed_in;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivechain.reset_encoders();
    	Robot.drivechain.drive_value(speed, speed);
    	DriverStation.reportWarning("Starting drive command: speed " + speed + ", distance " + inches, true);
    	Robot.drivechain.gyro_reset();
    }

    // Called repeatedly when this Command is scheduled to run
    /*protected void execute() {
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
    	Robot.drivechain.drive_value(lMod * speed, rMod * speed);
    	hasRun = true;
    }
    */
    
    protected void execute() {
    	Robot.drivechain.drive_value(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    /*protected boolean isFinished() {
    	boolean val = ((Robot.drivechain.get_left_encoder_raw_feet() >= feet) && (Robot.drivechain.get_right_encoder_raw_feet() >= feet));
    	DriverStation.reportWarning("Ran isFinished. Got result: " + val + ". Feet: " + feet + " LF: " + Robot.drivechain.get_left_encoder_raw_feet() + ". RF: " + Robot.drivechain.get_right_encoder_raw_feet(), true);
        return (val || !hasRun);
    }*/
    
    protected boolean isFinished() {
    	if (inches >= 0) {
    		return (Robot.drivechain.get_left_encoder_raw_inches() > inches);
    	} else {
    		return (Robot.drivechain.get_left_encoder_raw_inches() < inches);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivechain.drive_value(0.0, 0.0);
    	DriverStation.reportWarning("Finishing drive command: speed " + speed + ", distance " + inches, true);
    	DriverStation.reportWarning("Final gyro value: " + Robot.drivechain.gyro_angle(), true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivechain.drive_value(0.0, 0.0);
    	DriverStation.reportWarning("Interrupting drive command: speed " + speed + ", distance " + inches, true);
    }
}
