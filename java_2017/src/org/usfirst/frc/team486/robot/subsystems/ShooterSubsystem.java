package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.Robot;
import org.usfirst.frc.team486.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Solenoid shooter_reg = new Solenoid(RobotMap.SHOOTER_REG_PIN);
	
	private Talon shooter = new Talon(RobotMap.SHOOTER_PIN);
	
	public Encoder shooter_enc = 
			new Encoder(RobotMap.ENCODER_PIN_B, RobotMap.ENCODER_PIN_A, false, Encoder.EncodingType.k1X);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void spin(double power){
    	shooter.set(power);
    }
    
    public void stop(){
    	shooter.set(0);
    }
    
    public void open(){
    	shooter_reg.set(true);
    }
    
    public void close(){
    	shooter_reg.set(false);
    }
}

