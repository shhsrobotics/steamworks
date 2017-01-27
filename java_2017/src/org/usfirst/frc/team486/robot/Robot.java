
package org.usfirst.frc.team486.robot;

import org.usfirst.frc.team486.filter.Green;
import org.usfirst.frc.team486.robot.camera.Display;
import org.usfirst.frc.team486.robot.camera.Track;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team486.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team486.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team486.robot.subsystems.CompressorSubsystem;

import java.util.List;

import org.opencv.imgproc.Imgproc;
import org.opencv.core.*;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends IterativeRobot {

	public static final CameraSubsystem camera = new CameraSubsystem();
	public static final DriveSubsystem drivechain = new DriveSubsystem();
	public static final CompressorSubsystem compressor = new CompressorSubsystem();
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	private Thread camthread_literal;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		
		camthread_literal = new Thread(() -> {
			
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(640, 480);
			
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
			
			Mat source = new Mat();
			Mat hsv = new Mat();
			Mat filtered = new Mat();
			
			Display display = new Display();
			Green green_prep = new Green();
			Track track = new Track();
			
			while (!Thread.interrupted()) {
			
				cvSink.grabFrame(source);
				//CONVERTING IMAGE TYPES (source is BGR)
				Imgproc.cvtColor(source, hsv, Imgproc.COLOR_BGR2HSV);
				
				//Filter through color ranges
				if (CamThread.FILTER_COLOR == "green"){
					//RED
					green_prep.process(hsv);
					filtered = green_prep.hsvThresholdOutput();
				} else if (CamThread.FILTER_COLOR == "blue") {
					//BLUE
				}
				
				List<MatOfPoint> contours = track.find_blobs(filtered);
				track.find_dimensions(contours);
				SmartDashboard.putNumber("blobs", track.get_num_blobs());
				SmartDashboard.putNumber("center_x", Track.get_center().x);
				SmartDashboard.putNumber("center_y", Track.get_center().y);
				SmartDashboard.putNumber("correction", Track.get_correction());
				SmartDashboard.putNumber("offset", Track.get_offset());
				
				if (track.get_found()){
					display.draw_point(source, Track.get_center(), "red");
				}
				
				//DISPLAY IMAGE
				outputStream.putFrame(source);
				
				track.reset();
			}	
		});
		
		camthread_literal.start();
	}

	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.camera.light_off();
		//camthread.interrupt();
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
		Robot.camera.light_on();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		Robot.camera.light_on();
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
