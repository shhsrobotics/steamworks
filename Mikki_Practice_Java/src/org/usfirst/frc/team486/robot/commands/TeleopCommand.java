package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopCommand extends Command {

	public TeleopCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivechain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivechain.initdrive();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivechain.drive(Robot.oi.rightstick, Robot.oi.leftstick);
    }
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
