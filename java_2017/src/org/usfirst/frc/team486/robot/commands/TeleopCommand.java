package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;
import org.usfirst.frc.team486.robot.camera.Track;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopCommand extends Command {

    public TeleopCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivechain);
    	requires(Robot.compressor);
    	requires(Robot.camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivechain.initdrive();
    	Robot.compressor.on();
    	Robot.camera.light_on();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.trackbutton.get()){
    		Robot.drivechain.drive_value(
    				Robot.camera.get_status().get_correction(), 
    				-Robot.camera.get_status().get_correction());
    	} else {
    		Robot.drivechain.drive_joystick(Robot.oi.rightstick, Robot.oi.leftstick);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.compressor.off();
    	Robot.camera.light_off();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.compressor.off();
    	Robot.camera.light_off();
    }
}
