
package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.robot.commands.AutoDriveDistance;
import org.usfirst.frc.team486.robot.commands.AutoDriveFiveFeetCommandGroup;
import org.usfirst.frc.team486.robot.commands.GearLiftCommand;
import org.usfirst.frc.team486.robot.commands.ShooterCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team486.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team486.robot.subsystems.GearGrabSubsystem;
import org.usfirst.frc.team486.robot.subsystems.GearLiftSubsystem;
import org.usfirst.frc.team486.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team486.robot.subsystems.WinchSubsystem;
import org.usfirst.frc.team486.robot.triggers.OpstickBackTrigger;
import org.usfirst.frc.team486.robot.triggers.OpstickForwardTrigger;
import org.usfirst.frc.team486.robot.triggers.Opstick_1_3_Trigger;
import org.usfirst.frc.team486.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team486.robot.subsystems.CompressorSubsystem;

public class Robot extends IterativeRobot {
	Command autonomousCommand;

	public static final CameraSubsystem camera = new CameraSubsystem();
	public static final DriveSubsystem drivechain = new DriveSubsystem();
	public static final CompressorSubsystem compressor = new CompressorSubsystem();
	public static final GearGrabSubsystem gear_grab = new GearGrabSubsystem();
	public static final GearLiftSubsystem gear_lift = new GearLiftSubsystem();
	public static final ShooterSubsystem shooter = new ShooterSubsystem();
	public static final WinchSubsystem winch = new WinchSubsystem();
	public static OI oi;
	
	private final OpstickBackTrigger opstickbacktrigger = new OpstickBackTrigger();
	private final OpstickForwardTrigger opstickforwardtrigger = new OpstickForwardTrigger();
	SendableChooser chooser = new SendableChooser();
	
	//Thread visionThread;
	Thread encoderThread;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Drive forward 5 feet (WARNING: UNTESTED)", new AutoDriveDistance(5.0, 0.8));
		SmartDashboard.putData("Autonomous Mode", chooser);
		oi = new OI();
		//autonomousCommand = (Command)chooser.getSelected();
		shooter.reset();
		
		opstickbacktrigger.whileActive(new GearLiftCommand(true));
		opstickforwardtrigger.whileActive(new GearLiftCommand(false));
	}

	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = new AutoDriveDistance(5.0, 0.8);
		DriverStation.reportWarning("Auto hardwired: " + autonomousCommand.getName(), true);
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
