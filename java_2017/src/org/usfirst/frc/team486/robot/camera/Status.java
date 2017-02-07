package org.usfirst.frc.team486.robot.camera;

import org.opencv.core.Point;

public class Status {
	
	// A point on the image (x and y coords)
	private Point center;
	
	// How far away the computer thinks the reflective tape is
	private double distance;
	
	// How far off the center the target is (-100 to 100) as a percentage of image width
	private double offset_percentage;
	
	// prescribed correction (between -1 and 1)
	private double correction;
	
	private boolean led_on;
	
	public Status(Point center_in, double distance_in, double offset_percentage_in, double correction_in){
		this.center = center_in;
		this.distance = distance_in;
		this.offset_percentage = offset_percentage_in;
		this.correction = correction_in;
		this.led_on = false;
	}
	
	public void update(Status new_status){
		this.center = new_status.center;
		this.distance = new_status.distance;
		this.offset_percentage = new_status.offset_percentage;
	}
	
	public Point get_center(){
		return this.center;
	}
	
	public double get_distance(){
		return this.distance;
	}
	
	public double get_offset_percentage(){
		return this.offset_percentage;
	}
	
	public double get_correction(){
		return this.correction;
	}
	
	public void led_on(){
		this.led_on = true;
	}
	
	public void led_off(){
		this.led_on = false;
	}
	
	public boolean get_led(){
		return this.led_on;
	}
	
}
