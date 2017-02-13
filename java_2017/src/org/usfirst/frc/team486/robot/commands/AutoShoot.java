package org.usfirst.frc.team486.robot.commands;

import org.usfirst.frc.team486.robot.Robot;
import org.usfirst.frc.team486.robot.control.ShootControl;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoShoot extends Command {
	
	private double target;
	private double offset;
	private double correction;
	private double max;
	private ShootControl control = new ShootControl(0.1,20000);
	
    public AutoShoot(double rate) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.target = rate;
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	offset = (Robot.shooter.get_rate() - target);
    	max = Robot.oi.opstick.getThrottle();
    	SmartDashboard.putNumber("correction", max);
    	control.update_max(max);
    	correction = control.get_correction(offset);
    	Robot.shooter.spin(correction); // since backwards
    	SmartDashboard.putNumber("SHOOTER_RATE", Robot.shooter.get_rate());
    	if (Robot.oi.shootregdebug.get()){
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
    	Robot.shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.stop();
    }
}
