package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
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
		DriverStation.reportWarning("Starting Teleop command", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Drive slower if either slow button is pressed
    	if (Robot.oi.left_slow.get() || Robot.oi.right_slow.get()){
    		Robot.drivechain.slow_drive_joystick(Robot.oi.leftstick, Robot.oi.rightstick);
    	} else {
    		Robot.drivechain.drive_joystick(Robot.oi.rightstick, Robot.oi.leftstick);
    	}
    		
    	SmartDashboard.putNumber("LEFT_RAW", Robot.drivechain.get_left_encoder_raw());
    	SmartDashboard.putNumber("LEFT_RATE", Robot.drivechain.get_left_encoder_rate());
    	SmartDashboard.putNumber("RIGHT RAW", Robot.drivechain.get_right_encoder_raw());
    	SmartDashboard.putNumber("RIGHT_RATE", Robot.drivechain.get_right_encoder_rate());
    	SmartDashboard.putNumber("Analog gyro angle", Robot.drivechain.gyro_angle());
    	// Use the third axis on the operator stick as a switch for the camera light
    	if (Robot.oi.opstick.getRawAxis(2) > 0.5) {
    		Robot.camera.light_off();
    	} else {
    		Robot.camera.light_on();
    	}
    	//DriverStation.reportWarning("Just put SmartDashboard values", true);
    	SmartDashboard.putNumber("SHOOTER_RATE", Robot.shooter.get_rate());
    	SmartDashboard.putNumber("SHOOTER_RATE_GAUGE", Robot.shooter.get_rate());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.compressor.off();
    	Robot.camera.light_off();
    	DriverStation.reportWarning("Ending Teleop command", true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.compressor.off();
    	Robot.camera.light_off();
    	DriverStation.reportWarning("Teleop command interrupted", true);
    }
}
