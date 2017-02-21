
package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.robot.commands.LiftGear;
import org.usfirst.frc.team486.robot.commands.groups.AutoMode1;
import org.usfirst.frc.team486.robot.commands.groups.AutoMode2;
import org.usfirst.frc.team486.robot.commands.groups.AutoMode3;
import org.usfirst.frc.team486.robot.commands.groups.TestMode1;
import org.usfirst.frc.team486.robot.commands.groups.TestMode2;
import org.usfirst.frc.team486.robot.commands.groups.TestMode3;
import org.usfirst.frc.team486.robot.commands.AutoPrintDebugStatements;
import org.usfirst.frc.team486.robot.commands.GrabGear;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team486.robot.subsystems.Chassis;
import org.usfirst.frc.team486.robot.subsystems.Winch;
import org.usfirst.frc.team486.robot.triggers.CloseTrigger;
import org.usfirst.frc.team486.robot.triggers.LiftTrigger;
import org.usfirst.frc.team486.robot.triggers.LowerTrigger;
import org.usfirst.frc.team486.robot.triggers.OpenTrigger;
import org.usfirst.frc.team486.robot.subsystems.Camera;
import org.usfirst.frc.team486.robot.subsystems.Claw;
import org.usfirst.frc.team486.robot.subsystems.Regulator;
import org.usfirst.frc.team486.robot.subsystems.ShooterPID;
import org.usfirst.frc.team486.robot.subsystems.AirCompressor;

public class Robot extends IterativeRobot {

	// ----------------------------------------------------------
	// SUBSYSTEM INSTANTIATIONS
	// ----------------------------------------------------------
	public static final Camera camera = new Camera();
	public static final Chassis drivechain = new Chassis();
	public static final AirCompressor compressor = new AirCompressor();
	public static final ShooterPID shooter = new ShooterPID();
	public static final Winch winch = new Winch();
	public static final Regulator regulator = new Regulator(); // for letting balls into the shooter
	public static final Claw claw = new Claw(); // for grabbing and lifting gears
	public static OI oi; // for controlling the robot

	// ----------------------------------------------------------
	// TRIGGER INSTANTIATIONS
	// ----------------------------------------------------------
	private final OpenTrigger open_trigger = new OpenTrigger(); // for opening the claw to grab a gear
	private final CloseTrigger close_trigger = new CloseTrigger(); // for closing the claw to grab a gear
	private final LiftTrigger lift_trigger = new LiftTrigger(); // for lifting the claw to lift a gear
	private final LowerTrigger lower_trigger = new LowerTrigger(); // for lowering the claw to lower a gear
	
	// ----------------------------------------------------------
	// AUTONOMOUS COMMAND INSTANTIATIONS
	// ----------------------------------------------------------
	Command autonomousCommand; // empty variable for an autonomous command
	SendableChooser<Command> chooser = new SendableChooser<>(); // the chooser on the smart dash-board that lets the user select autonomous

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		// ------------------------------------------------------
		// CAMERA CODE
		// ------------------------------------------------------
		CameraServer.getInstance().startAutomaticCapture(); // Getting the camera feed and sending it to smart dash-board
		
		// ------------------------------------------------------
		// AUTONOMOUS CHOOSER
		// ------------------------------------------------------
		chooser.addDefault("Just print debug statements", new AutoPrintDebugStatements(10.0)); // debug command
		chooser.addObject("Test Mode 1", new TestMode1()); // copy of auto mode 1, but distances are lowered and the robot travels slower
		chooser.addObject("Test Mode 2", new TestMode2()); // copy of auto mode 2, but distances are lowered and the robot travels slower
		chooser.addObject("Test Mode 3", new TestMode3()); // copy of auto mode 3, but distances are lowered and the robot travels slower
		chooser.addObject("AM1: Center Start", new AutoMode1()); // auto mode 1, code for a center start (relative to driver station)
		chooser.addObject("AM2: Right Start", new AutoMode2()); // auto mode 2, code for a right sided start (relative to driver station)
		chooser.addObject("AM3: Left Start", new AutoMode3()); // auto mode 3, code for a left sided start (relative to driver station)
		SmartDashboard.putData("Auto Chooser", chooser); // putting the added auto modes onto the SmartDashboard
		
		// ------------------------------------------------------
		// OI INSTANTIATION
		// ------------------------------------------------------
		oi = new OI(); // instantiating OI
		
		// ------------------------------------------------------
		// TRIGGER ASSIGNMENTS
		// ------------------------------------------------------
		open_trigger.whileActive(new GrabGear(false)); // while open_trigger is triggered, open the gear grabber
		close_trigger.whileActive(new GrabGear(true)); // while close_trigger is triggered, close the gear grabber
		lift_trigger.whileActive(new LiftGear(true)); // while lift_trigger is triggered, lift the gear grabber
		lower_trigger.whileActive(new LiftGear(false)); // while lower_trigger is triggered, lower the gear grabber
		
		// ------------------------------------------------------
		// GYROSCOPE CALIBRATION
		// ------------------------------------------------------
		Robot.drivechain.gyro_start(); // calibrating the gyroscope right as the robot starts up
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
		
		// ------------------------------------------------------
		// AUTONOMOUS INITIATION
		// ------------------------------------------------------
		autonomousCommand = chooser.getSelected(); // get the command to use for autonomous from the smart dash-board selection
		if (autonomousCommand != null) // if the autonomous command is not empty (i.e. if some command was selected)
			autonomousCommand.start(); // start autonomous command
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
		// ------------------------------------------------------
		// AUTONOMOUS INITIATION
		// ------------------------------------------------------
		if (autonomousCommand != null) // if the autonomous command is not empty (i.e. if some command was selected)
			autonomousCommand.cancel(); // end the autonomous as teleop begins
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
