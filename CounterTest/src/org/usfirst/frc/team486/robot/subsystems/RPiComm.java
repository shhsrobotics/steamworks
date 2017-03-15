package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RPiComm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Counter counter = new Counter(RobotMap.COUNTER_PIN);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void init(){
    	counter.setSemiPeriodMode(true);
    }
    
    public double get() {
    	return Math.round(counter.getPeriod()*10000);
    }
}

