package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Teleop extends Command {

    public Teleop() {
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
    	if (Robot.oi.slowbutton.get()){
    		Robot.drivechain.slow_drive_joystick(Robot.oi.leftstick, Robot.oi.rightstick);
    	} else {
    		Robot.drivechain.drive_joystick(Robot.oi.rightstick, Robot.oi.leftstick);
    	}
    		
    	SmartDashboard.putNumber("LEFT_RAW", Robot.drivechain.get_left_encoder_raw());
    	SmartDashboard.putNumber("LEFT_RATE", Robot.drivechain.get_left_encoder_rate());
    	SmartDashboard.putNumber("RIGHT RAW", Robot.drivechain.get_right_encoder_raw());
    	SmartDashboard.putNumber("RIGHT_RATE", Robot.drivechain.get_right_encoder_rate());
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
