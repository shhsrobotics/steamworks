package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.commands.TeleopCommand;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CompressorSubsystem extends Subsystem {

	Relay compressor = new Relay(RobotMap.COMPRESSOR_PIN);

    public void initDefaultCommand() {
    	setDefaultCommand(new TeleopCommand());
    }
    
    public void on(){
    	compressor.set(Value.kForward);
    }
    
    public void off(){
    	compressor.set(Value.kOff);
    }
}

