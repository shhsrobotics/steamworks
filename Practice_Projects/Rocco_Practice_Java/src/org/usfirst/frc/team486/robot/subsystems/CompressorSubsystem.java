package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.commands.CompressorCommand;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CompressorSubsystem extends Subsystem {

	private Compressor compressor = new Compressor(RobotMap.compressor_pin);
	
    public void initDefaultCommand() {
    	
    }
    public void on(){
    	compressor.setClosedLoopControl(true);
    }
    public void off(){
    	compressor.setClosedLoopControl(false);
    }
		// TODO Auto-generated method stub
}

