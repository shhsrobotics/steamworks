
package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.robot.commands.LiftGear;
import org.usfirst.frc.team486.robot.commands.groups.AutoCenter;
import org.usfirst.frc.team486.robot.commands.groups.AutoRight;
import org.usfirst.frc.team486.robot.commands.groups.AutoRightCam;
import org.usfirst.frc.team486.robot.commands.groups.AutoLeft;
import org.usfirst.frc.team486.robot.commands.groups.AutoLeftCam;
import org.usfirst.frc.team486.robot.commands.groups.TestCenter;
import org.usfirst.frc.team486.robot.commands.groups.TestRight;
import org.usfirst.frc.team486.robot.commands.groups.TestLeft;
import org.usfirst.frc.team486.robot.commands.AutoPrintDebugStatements;
import org.usfirst.frc.team486.robot.commands.DeliverGear;
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
import org.usfirst.frc.team486.robot.triggers.CamOverrideTrigger;
import org.usfirst.frc.team486.robot.triggers.CloseTrigger;
import org.usfirst.frc.team486.robot.triggers.LiftTrigger;
import org.usfirst.frc.team486.robot.triggers.LowerTrigger;
import org.usfirst.frc.team486.robot.triggers.OpenTrigger;
import org.usfirst.frc.team486.robot.subsystems.LED;
import org.usfirst.frc.team486.robot.subsystems.Claw;
import org.usfirst.frc.team486.robot.subsystems.RPiComm;
import org.usfirst.frc.team486.robot.subsystems.Regulator;
import org.usfirst.frc.team486.robot.subsystems.ShooterPID;
import org.usfirst.frc.team486.robot.subsystems.AirCompressor;

public class Robot extends IterativeRobot {

	// ----------------------------------------------------------
	// SUBSYSTEM INSTANTIATIONS
	// ----------------------------------------------------------
	public static final LED led = new LED();
	public static final Chassis chassis = new Chassis();
	public static final AirCompressor compressor = new AirCompressor();
	public static final ShooterPID shooter = new ShooterPID();
	public static final Winch winch = new Winch();
	public static final Regulator regulator = new Regulator(); // for letting balls into the shooter
	public static final Claw claw = new Claw(); // for grabbing and lifting gears
	public static final RPiComm rpi_comm = new RPiComm();
	public static OI oi; // for controlling the robot

	// ----------------------------------------------------------
	// TRIGGER INSTANTIATIONS
	// ----------------------------------------------------------
	private final OpenTrigger open_trigger = new OpenTrigger(); // for opening the claw to grab a gear
	private final CloseTrigger close_trigger = new CloseTrigger(); // for closing the claw to grab a gear
	private final LiftTrigger lift_trigger = new LiftTrigger(); // for lifting the claw to lift a gear
	private final LowerTrigger lower_trigger = new LowerTrigger(); // for lowering the claw to lower a gear
	private final CamOverrideTrigger cam_override_trigger = new CamOverrideTrigger();
	
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
		chooser.addObject("Center Test", new TestCenter()); // copy of auto mode 1, but distances are lowered and the robot travels slower
		chooser.addObject("Right Test", new TestRight()); // copy of auto mode 2, but distances are lowered and the robot travels slower
		chooser.addObject("Left Test", new TestLeft()); // copy of auto mode 3, but distances are lowered and the robot travels slower
		chooser.addObject("Center Start", new AutoCenter()); // auto mode 1, code for a center start (relative to driver station)
		chooser.addObject("Right Start", new AutoRight()); // auto mode 2, code for a right sided start (relative to driver station)
		chooser.addObject("Left Start", new AutoLeft()); // auto mode 3, code for a left sided start (relative to driver station)
		chooser.addObject("Right Start Cam", new AutoRightCam());
		chooser.addObject("Left Start Cam", new AutoLeftCam());
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
		cam_override_trigger.whileActive(new DeliverGear(RobotMap.TURN_SLOW_SPEED));
		
		// ------------------------------------------------------
		// GYROSCOPE CALIBRATION
		// ------------------------------------------------------
		Robot.chassis.gyro_start(); // calibrating the gyroscope right as the robot starts up
		
		Robot.rpi_comm.init();
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
