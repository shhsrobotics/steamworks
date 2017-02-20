package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;
import org.usfirst.frc.team486.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoShootPID extends Command {
	
	double rate;

    public AutoShootPID(double rate) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	this.rate = rate;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putData("PID Values", Robot.shooter.getPIDController());
    	Robot.shooter.setSetpoint(rate);
    	Robot.shooter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("SHOOTER_RATE", Robot.shooter.get_rate() * -1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean out = false;
    	if (this.rate == RobotMap.SHOOTSPEED_1){
    		out = !Robot.oi.shootauto1.get();
    	} else if (this.rate == RobotMap.SHOOTSPEED_2) {
    		out = !Robot.oi.shootauto2.get();
    	} else if (this.rate == RobotMap.SHOOTSPEED_3) {
    		out = !Robot.oi.shootauto3.get();
    	}
        return out;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.stop();
    }
}
