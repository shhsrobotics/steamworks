package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.camera.Frame;
import org.usfirst.frc.team486.robot.camera.Status;
import org.usfirst.frc.team486.robot.commands.TeleopCommand;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Status status;
	private Frame frame = new Frame(320,240);
	
	private Relay led_switch = new Relay(RobotMap.LED_PIN);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void light_on(){
    	led_switch.set(Relay.Value.kOn);
    }
    
    public void light_off(){
    	led_switch.set(Relay.Value.kOff);
    }
    
    public Status get_status(){
		return this.status;
	}
    
    public Frame get_frame(){
    	return this.frame;
    }
}

