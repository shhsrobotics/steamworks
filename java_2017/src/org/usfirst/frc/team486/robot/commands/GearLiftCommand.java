package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearLiftCommand extends Command {
	
	private boolean state;

    public GearLiftCommand(boolean state_in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear_lift);
    	this.state = state_in;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (this.state == true){
    		Robot.gear_lift.raise();
    	} else {
    		Robot.gear_lift.drop();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
