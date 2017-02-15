
package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.robot.commands.LiftGear;
import org.usfirst.frc.team486.robot.commands.Shoot;
import org.usfirst.frc.team486.robot.commands.AutoDriveDistance;
import org.usfirst.frc.team486.robot.commands.GrabGear;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team486.robot.subsystems.Chassis;
import org.usfirst.frc.team486.robot.subsystems.Winch;
import org.usfirst.frc.team486.robot.triggers.OpstickBackTrigger;
import org.usfirst.frc.team486.robot.triggers.OpstickForwardTrigger;
import org.usfirst.frc.team486.robot.triggers.Opstick_1_3_Trigger;
import org.usfirst.frc.team486.robot.subsystems.Camera;
import org.usfirst.frc.team486.robot.subsystems.Claw;
import org.usfirst.frc.team486.robot.subsystems.Shooter;
import org.usfirst.frc.team486.robot.subsystems.AirCompressor;

public class Robot extends IterativeRobot {

	public static final Camera camera = new Camera();
	public static final Chassis drivechain = new Chassis();
	public static final AirCompressor compressor = new AirCompressor();
	public static final Shooter shooter = new Shooter();
	public static final Winch winch = new Winch();
	public static OI oi;
	
	public static final Claw claw = new Claw();
	
	private final OpstickBackTrigger opstickbacktrigger = new OpstickBackTrigger();
	private final OpstickForwardTrigger opstickforwardtrigger = new OpstickForwardTrigger();

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	//Thread visionThread;
	Thread encoderThread;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Drive forward 5 feet (WARNING: UNTESTED)", new AutoDriveDistance(5.0, 0.4));
		SmartDashboard.putData("Auto Chooser", chooser);
		oi = new OI();
		
		//shooter.reset();
		
		opstickbacktrigger.whileActive(new LiftGear(true));
		opstickforwardtrigger.whileActive(new LiftGear(false));
		
		Robot.drivechain.gyro_start();
		Timer.delay(5);
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
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
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
		if (autonomousCommand != null)
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
