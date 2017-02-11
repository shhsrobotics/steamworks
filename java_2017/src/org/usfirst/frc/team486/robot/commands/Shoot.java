package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shoot extends Command {
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.shooter.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.shooter.spin(Robot.oi.opstick.getY());
//    	SmartDashboard.putNumber("SHOOTER_RAW", Robot.shooter.get_raw());
//    	SmartDashboard.putNumber("SHOOTER_RATE", Robot.shooter.get_rate());
//    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.shooter.stop();
    	SmartDashboard.putNumber("correction", 0);
    }
}
