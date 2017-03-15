package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPrintDebugStatements extends Command {
	
	Timer time = new Timer();
	double duration;

    public AutoPrintDebugStatements(double duration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivechain);
    	this.duration = duration;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriverStation.reportWarning("Initialize AutoPrintDebugStatements command", true);
    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriverStation.reportWarning("Execute AutoPrintDebugStatements command", true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (time.get() > duration);
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriverStation.reportWarning("End AutoPrintDebugStatements command", true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	DriverStation.reportWarning("Interrupted AutoPrintDebugStatements command", true);
    }
}
