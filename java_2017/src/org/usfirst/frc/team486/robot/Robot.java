
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

	public static final Camera camera = new Camera();
	public static final Chassis drivechain = new Chassis();
	public static final AirCompressor compressor = new AirCompressor();
	public static final ShooterPID shooter = new ShooterPID();
	public static final Winch winch = new Winch();
	public static final Regulator regulator = new Regulator();
	public static final Claw claw = new Claw();
	public static OI oi;

	private final OpenTrigger open_trigger = new OpenTrigger();
	private final CloseTrigger close_trigger = new CloseTrigger(); 
	private final LiftTrigger lift_trigger = new LiftTrigger();
	private final LowerTrigger lower_trigger = new LowerTrigger();
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	//Thread visionThread;
	//Thread encoderThread;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
		chooser.addDefault("Just print debug statements", new AutoPrintDebugStatements(10.0));
		chooser.addObject("Test Mode 1", new TestMode1());
		chooser.addObject("Test Mode 2", new TestMode2());
		chooser.addObject("Test Mode 3", new TestMode3());
		chooser.addObject("AM1: Center Start", new AutoMode1());
		chooser.addObject("AM2: Right Start", new AutoMode2());
		chooser.addObject("AM3: Left Start", new AutoMode3());
		SmartDashboard.putData("Auto Chooser", chooser);
		//autonomousCommand = chooser.getSelected();
		oi = new OI();
		
		//shooter.reset();
		
		open_trigger.whileActive(new GrabGear(false));
		close_trigger.whileActive(new GrabGear(true));
		lift_trigger.whileActive(new LiftGear(true));
		lower_trigger.whileActive(new LiftGear(false));
		
		Robot.drivechain.gyro_start();
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
