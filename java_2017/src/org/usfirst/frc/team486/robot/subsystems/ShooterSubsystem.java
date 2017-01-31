package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.Robot;
import org.usfirst.frc.team486.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Talon shooter = new Talon(RobotMap.SHOOTER_PIN);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void spin(double power){
    	shooter.set(power);
    }
    
    public void spin(){
    	shooter.set(Robot.oi.opstick.getAxisType(3));
    }
    
    public void stop(){
    	shooter.set(0);
    }
}

