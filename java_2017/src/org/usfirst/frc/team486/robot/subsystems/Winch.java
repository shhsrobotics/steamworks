package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Talon winch = new Talon(RobotMap.WINCH_PIN);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double power){
    	winch.set(power);
    }
}

