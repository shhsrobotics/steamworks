package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.commands.CompressorCommand;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CompressorSubsystem extends Subsystem {

	Compressor compressor = new Compressor(RobotMap.Compressor_pin);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new CompressorCommand());
    }
    public void start(){
    	compressor.setClosedLoopControl(true);
    }
    public void end(){
    	compressor.setClosedLoopControl(false);
    }
}

