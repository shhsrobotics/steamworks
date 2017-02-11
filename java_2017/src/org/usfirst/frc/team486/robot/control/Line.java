package org.usfirst.frc.team486.robot.control;

import org.opencv.core.Point;

public class Line {
	private Point p1;
	private Point p2;
	private double m;
	private double b;
	
	public Line(Point p1_in, Point p2_in){
		this.p1 = p1_in;
		this.p2 = p2_in;
		this.m = (p2.y - p1.y) / (p2.x - p1.x);
		this.b = p2.y - m*p2.x;
	}
	
	public double get_val(double x){
		return (this.m*x + this.b);
	}
}
