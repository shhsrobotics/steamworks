package org.usfirst.frc.team486.control;

import org.opencv.core.Point;

public class Line {
	
	private Point p1;
	private Point p2;
	
	private double m;
	private double b;
	
	public Line(Point p1_inst, Point p2_inst){
		this.p1 = p1_inst;
		this.p2 = p2_inst;
		double delta_x = this.p2.x - this.p1.x;
		double delta_y = this.p2.y - this.p1.y;
		this.m = delta_y / delta_x;
		this.b = this.p1.y - (this.m * this.p1.x);
	}
	
	public double evaluate(double x){
		return (double)(this.m*x + this.b);
	}
}
