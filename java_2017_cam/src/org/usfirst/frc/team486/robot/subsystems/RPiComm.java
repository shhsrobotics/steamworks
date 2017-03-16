package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.RPi.Packet;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    
    public Packet get() {
    	Packet packet = new Packet(Math.round(counter.getPeriod()*10000));
    	
    	SmartDashboard.putNumber("RPi Value", packet.get_value());
    	SmartDashboard.putNumber("RPi Percentage", packet.get_percentage());
    	SmartDashboard.putBoolean("RPi Horizontal", packet.get_horizontal());
    	SmartDashboard.putBoolean("RPi Vertical", packet.get_vertical());
    	SmartDashboard.putBoolean("RPi Light", packet.get_light());
    	
    	return packet;
    }
}

