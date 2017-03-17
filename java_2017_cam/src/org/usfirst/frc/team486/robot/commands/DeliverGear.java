package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;
import org.usfirst.frc.team486.robot.RPi.Packet;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CamTurn extends Command {
	
	private Packet packet;
	private double speed;

    public CamTurn(double speed_in) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed_in;
    	requires(Robot.chassis);
    	requires(Robot.rpi_comm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	packet = Robot.rpi_comm.get();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	packet = Robot.rpi_comm.get();
    	// if less than zero, turn left
    	if (packet.get_percentage() < 0){
    		Robot.chassis.drive_value(-this.speed, this.speed);
    	}
    	// if greater than zero, turn right
    	else if (packet.get_percentage() > 0){
    		Robot.chassis.drive_value(this.speed, -this.speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (packet.get_percentage() == 0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.drive_value(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassis.drive_value(0, 0);
    }
}
