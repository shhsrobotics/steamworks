package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearGrabSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Solenoid grab = new Solenoid(RobotMap.GRAB_PIN);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void release(){
    	grab.set(false);
    }
    
    public void grab(){
    	grab.set(true);
    }
    
    public boolean get(){
    	return grab.get();
    }
    
}

