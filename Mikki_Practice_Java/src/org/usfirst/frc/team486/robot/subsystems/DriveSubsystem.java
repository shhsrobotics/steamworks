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

	private Talon left_1 = new Talon(RobotMap.LEFT_1_PIN);
	private Talon left_2 = new Talon(RobotMap.LEFT_2_PIN);
	private Talon right_1 = new Talon(RobotMap.RIGHT_1_PIN);
	private Talon right_2 = new Talon(RobotMap.RIGHT_2_PIN);
	private RobotDrive drive = new RobotDrive(left_1, left_2, right_1, right_2);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleopCommand());
    }
    public void drive(Joystick leftStick, Joystick rightStick) {
    	drive.tankDrive(leftStick, rightStick);
    }
    public void initdrive() {
    	//may need to change inversions
    	drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
    	drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    }
}

