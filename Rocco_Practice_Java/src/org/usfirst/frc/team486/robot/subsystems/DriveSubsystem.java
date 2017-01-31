package org.usfirst.frc.team486.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.commands.DriveCommand;


/**
 *
 */
public class DriveSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Talon left1 = new Talon(RobotMap.Left_Drive1);
	private Talon left2 = new Talon(RobotMap.Left_Drive2);
	private Talon right1 = new Talon(RobotMap.Right_Drive1);
	private Talon right2 = new Talon(RobotMap.Right_Drive2);
	
	private RobotDrive  drive = new RobotDrive(left1, left2, right1, right2);
	public void drive(Joystick left, Joystick right){
		drive.tankDrive(left, right);
	}
	public void init_drive(){
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveCommand());
    }
}