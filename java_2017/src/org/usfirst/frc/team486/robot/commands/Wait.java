package org.usfirst.frc.team486.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {
	Timer t;
	double duration;

    public Wait(double duration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	t = new Timer();
    	this.duration = duration;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t.reset();
    	t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return t.get() > duration;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
