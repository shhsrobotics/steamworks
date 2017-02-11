package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Solenoid claw = new Solenoid(RobotMap.CLAW_PIN);
	private Solenoid lift = new Solenoid(RobotMap.LIFT_PIN);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	}
    public void grab(){
		claw.set(true);
	}
	public void release(){
		claw.set(false);
	}
	public boolean get(){
		return claw.get();
	}
	public void raise(){
		lift.set(true);
	}
	public void lower(){
		lift.set(false);
	}
	public boolean status(){
		return lift.get();
	}
}

