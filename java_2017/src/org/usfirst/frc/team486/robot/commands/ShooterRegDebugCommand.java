package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterRegDebugCommand extends Command {

	private boolean state;
	
    public ShooterRegDebugCommand(boolean state_in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.state = state_in;
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (this.state){
    		Robot.shooter.open();
    	} else {
    		Robot.shooter.close();
    	}
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
