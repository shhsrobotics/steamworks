package org.usfirst.frc.team486.robot.control;

import org.opencv.core.Point;

public class ShootControl {
	
	private double max;
	private double delta = 20000;
	private Point p1;
	private Point p2;
	private Line calc_line;
	
	private double v_118_67k = 0.566;
	private double v_118_54k = 0.512;
	private double v_118_30k = 0.33;
	private Point p_118_67k = new Point(11.8, v_118_67k);
	private Point p_118_54k = new Point(11.8, v_118_54k);
	private Point p_118_30k = new Point(11.8, v_118_30k);
	
	private double v_125_67k = 0.512;
	private double v_125_54k = 0.449;
	private double v_125_30k = 0.314;
	private Point p_125_67k = new Point(12.5, v_125_67k);
	private Point p_125_54k = new Point(12.5, v_125_54k);
	private Point p_125_30k = new Point(12.5, v_125_30k);
	
	private Line l_67k = new Line(p_118_67k, p_125_67k);
	private Line l_54k = new Line(p_118_54k, p_125_54k);
	private Line l_30k = new Line(p_118_30k, p_125_30k);
	
	
	public ShootControl(double target, double voltage){
		this.update(target, voltage);
	}
		
	public void update(double target, double voltage){
		if (target == 67000){
			this.max = this.l_67k.get_val(voltage);
		} else if (target == 54000){
			this.max = this.l_54k.get_val(voltage);
		} else if (target == 30000){
			this.max = this.l_30k.get_val(voltage);
		} else {
			this.max = this.l_30k.get_val(voltage);
		}
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
