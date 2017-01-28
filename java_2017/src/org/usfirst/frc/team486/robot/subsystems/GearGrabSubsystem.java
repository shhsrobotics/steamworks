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
	
	private static boolean state = false;
	
	private Solenoid grab = new Solenoid(RobotMap.GRAB_PIN);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void release(){
    	this.state = false;
    	grab.set(false);
    }
    
    public void grab(){
    	this.state = true;
    	grab.set(true);
    }
    
    public void toggle(){
    	this.state = !this.state;
    	grab.set(this.state);
    }
    
}

