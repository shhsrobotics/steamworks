package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GrabSubsystem extends Subsystem {

    private Solenoid grab = new Solenoid(RobotMap.grab_pin);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void grab(){
    	grab.set(false);
    }
    public void release(){
    	grab.set(true);
    }
    public boolean get(){
    	return grab.get();
    }
}