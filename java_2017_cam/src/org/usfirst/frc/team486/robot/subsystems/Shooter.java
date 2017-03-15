package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Talon shooter = new Talon(RobotMap.SHOOTER_PIN);

	private Encoder shooter_enc = 
			new Encoder(RobotMap.SHOOTER_ENCODER_PIN_B, RobotMap.SHOOTER_ENCODER_PIN_A, false, Encoder.EncodingType.k1X);
	
	private PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public double get_voltage(){
		return pdp.getVoltage();
	}
	
    public void initDefaultCommand() {
    }

    public void spin(double power){
    	shooter.set(power);
    }
    
    public void stop(){
    	shooter.set(0);
    }
    
    public double get_raw(){
    	return shooter_enc.getRaw();
    }
    
    public double get_rate(){
    	return shooter_enc.getRate();
    }
    
    public void reset(){
    	shooter_enc.reset();
    }
}

