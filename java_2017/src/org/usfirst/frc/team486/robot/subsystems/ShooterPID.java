package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.commands.AutoShootPID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class ShooterPID extends PIDSubsystem {

    // Initialize your subsystem here
    public ShooterPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("ShooterPID", 0.0, 0.0, 0.0);
    	setAbsoluteTolerance(2500.0);
    	getPIDController().setContinuous(false);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new AutoShootPID());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return get_rate();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	spin(output);
    }
    
    // Normal stuff
    private Talon shooter = new Talon(RobotMap.SHOOTER_PIN);

	private Encoder shooter_enc = 
			new Encoder(RobotMap.SHOOTER_ENCODER_PIN_B, RobotMap.SHOOTER_ENCODER_PIN_A, false, Encoder.EncodingType.k1X);
	
	private PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public double get_voltage(){
		return pdp.getVoltage();
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
