package org.usfirst.frc.team486.robot.subsystems;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.commands.TeleopCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Talon left = new Talon(RobotMap.leftController);
	private Talon right = new Talon(RobotMap.rightController);
	private RobotDrive drive = new RobotDrive(left,right);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleopCommand());
    }
    public void drive(Joystick leftStick, Joystick rightStick) {
    	drive.tankDrive(leftStick, rightStick);
    }
    public void initdrive() {
    	drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
    	drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    }
}

