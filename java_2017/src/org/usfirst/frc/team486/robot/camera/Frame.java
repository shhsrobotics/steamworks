package org.usfirst.frc.team486.robot.camera;

import org.opencv.core.Point;

public class Frame {
	
	private String type;
	private Point center;
	private int width;
	private int height;
	
	public Frame(int width_in, int height_in){
		if ((width_in == 160) && (height_in == 120)){
			this.set_160x120();
		} else if ((width_in == 320) && (height_in == 240)){
			this.set_320x240();
		} else if ((width_in == 640) && (height_in == 480)){
			this.set_640x480();
		} else {
			//nothing
		}
	}
	
	private void set_160x120(){
		this.type = "160x120";
		this.width = 160;
		this.height = 120;
		this.center = new Point(80,60);
	}
	
	private void set_320x240(){
		this.type = "320x240";
		this.width = 320;
		this.height = 240;
		this.center = new Point(160,120);
	}
	
	private void set_640x480(){
		this.type = "640x480";
		this.width = 640;
		this.height = 480;
		this.center = new Point(320,240);
	}
	
	public String get_type(){
		return this.type;
	}
	
	public int get_width(){
		return this.width;
	}
	
	public int get_height(){
		return this.height;
	}
	
	public Point get_center(){
		return this.center;
	}
}
