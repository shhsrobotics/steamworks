package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.commands.Teleop;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AirCompressor extends Subsystem {

	Compressor compressor = new Compressor(RobotMap.COMPRESSOR_PIN);

    public void initDefaultCommand() {
    	setDefaultCommand(new Teleop());
    }
    
    public void on(){
    	compressor.setClosedLoopControl(true);
    }
    
    public void off(){
    	compressor.setClosedLoopControl(false);
    }
}