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
	private double voltage;
	private ShootControl control;
	
    public AutoShoot(double rate) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.target = rate;
    	requires(Robot.shooter);
    	this.control = new ShootControl(this.target, Robot.shooter.get_voltage());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.offset = (Robot.shooter.get_rate() - this.target);
    	this.voltage = Robot.shooter.get_voltage();
    	this.control.update(this.target, this.voltage);
    	this.correction = this.control.get_correction(this.offset);
    	SmartDashboard.putNumber("correction", this.correction);
    	Robot.shooter.spin(this.correction); // since backwards
    	SmartDashboard.putNumber("SHOOTER_RATE", Robot.shooter.get_rate());
    	if (Robot.oi.shootregdebug.get()){
    		Robot.shooter.open();
    	} else {
    		Robot.shooter.close();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean out = false;
    	if (this.target == 67000){
    		out = !Robot.oi.shootdebug67k.get();
    	} else if (this.target == 54000) {
    		out = !Robot.oi.shootdebug54k.get();
    	} else if (this.target == 30000) {
    		out = !Robot.oi.shootdebug30k.get();
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
