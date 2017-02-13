package org.usfirst.frc.team486.robot.control;

import org.opencv.core.Point;

public class ShootControl {
	
	private double max;
	private double delta;
	private Point p1;
	private Point p2;
	private Line calc_line;
	
	public ShootControl(double max_in, double delta_in){
		this.update(max_in, delta_in);
	}
	
	public void update(double max_in, double delta_in){
		this.max = max_in;
		this.delta = delta_in;
		this.p1 = new Point(-this.delta, this.max);
		this.p2 = new Point(this.delta, -this.max);
		this.calc_line = new Line(this.p1,this.p2);
	}
	
	public void update_max(double max_in){
		this.max = max_in;
		this.p1 = new Point(-this.delta, this.max);
		this.p2 = new Point(this.delta, -this.max);
		this.calc_line = new Line(this.p1,this.p2);
	}
	
	public void update_delta(double delta_in){
		this.delta = delta_in;
		this.p1 = new Point(-this.delta, this.max);
		this.p2 = new Point(this.delta, -this.max);
		this.calc_line = new Line(this.p1,this.p2);
	}
	
	public double get_correction(double offset){
		double correction = 0;
		if (Math.abs(offset) <= this.delta){
			correction = this.calc_line.get_val(offset);
		} else if (offset > this.delta){
			correction = -this.max;
		} else {
			correction = this.max;
		}
		return correction;
	}
	
}
