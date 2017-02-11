
package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.robot.camera.Status;
import org.usfirst.frc.team486.robot.commands.GearLiftCommand;
import org.usfirst.frc.team486.robot.commands.ShooterCommand;

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
//	private final Opstick_1_3_Trigger opstick_1_3_trigger = new Opstick_1_3_Trigger();
	
//	private Status current_status;
//	private int width = Robot.camera.get_frame().get_width();
//	private int height = Robot.camera.get_frame().get_height();

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
		oi = new OI();
		
		shooter.reset();
		shooter.start_encoder();
		
		opstickbacktrigger.whileActive(new GearLiftCommand(true));
		opstickforwardtrigger.whileActive(new GearLiftCommand(false));
//		opstick_1_3_trigger.whileActive(new ShooterCommand());
		encoderThread = new Thread(() -> {
			double time = 0;
			while(!Thread.interrupted()){
				double r1 = shooter.get_raw();
				Timer.delay(0.25);
				double r2 = shooter.get_raw();
				Timer.delay(0.25);
				double rate = (r1-r2)/(0.25);
				SmartDashboard.putNumber("shooter_rpm", rate);
				SmartDashboard.putNumber("shooter_distance", r2);
				time += 0.5;
				SmartDashboard.putNumber("correction", time);
			}
		});
		encoderThread.setDaemon(true);
		encoderThread.start();
//		visionThread = new Thread(() -> {
//			
//			// Get the UsbCamera from CameraServer
//			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
//			// Set the resolution
//			camera.setResolution(width, height);
//
//			// Get a CvSink. This will capture Mats from the camera
//			CvSink cvSink = CameraServer.getInstance().getVideo();
//			// Setup a CvSource. This will send images back to the Dashboard
//			CvSource outputStream = CameraServer.getInstance().putVideo("Blur", width, height);
//
//			// Mats are very memory expensive.
//			Mat source = new Mat();
//			Mat hsv = new Mat();
//			Mat filtered = new Mat();
//			
//			// Define camera package classes necessary
//			Track track = new Track();
//			Display display = new Display();
//			
//			// Define variables necessary for tracking
//			List<MatOfPoint> contours;
//			
//			// Define filters from filter package
//			Green green_prep = new Green();
//
//			// This cannot be 'true'. The program will never exit if it is. This
//			// lets the robot stop this thread when restarting robot code or
//			// deploying.
//			while (!Thread.interrupted()) {
//				// Tell the CvSink to grab a frame from the camera and put it
//				// in the source mat.  If there is an error notify the output.
//				if (cvSink.grabFrame(source) == 0) {
//					// Send the output the error.
//					outputStream.notifyError(cvSink.getError());
//					// skip the rest of the current iteration
//					continue;
//				}
//				// Convert color data type
//				Imgproc.cvtColor(source, hsv, Imgproc.COLOR_BGR2HSV);
//				//filter
//				green_prep.filter_hsv(hsv,filtered);
//				// find contours
//				contours = track.find_blobs(filtered);
//				// evaluates and updates current status, but does not update camera status
//				current_status = track.find_center(contours);
//				// display status
////				SmartDashboard.putNumber("center_x", current_status.get_center().x);
////				SmartDashboard.putNumber("center_y", current_status.get_center().y);
//				// if found, draw target center
//				if (current_status.get_distance() != -1){
//					display.draw_point(source, current_status.get_center(), "red");
//				}
//				// Give the output stream a new image to display
//				outputStream.putFrame(source);
//			}
//		});
//		visionThread.setDaemon(true);
//		visionThread.start();
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