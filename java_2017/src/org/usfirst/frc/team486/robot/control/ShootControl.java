package org.usfirst.frc.team486.robot.control;

import org.opencv.core.Point;

public class ShootControl {
	
	private double max = 0.5;
	private double delta = 20000;
	private Point p1 = new Point(-delta, max);
	private Point p2 = new Point(delta, -max);
	private Line calc_line = new Line(p1,p2);
	
	public double get_correction(double offset){
		double correction = 0;
		if (Math.abs(offset) <= delta){
			correction = calc_line.get_val(offset);
		} else if (offset > delta){
			correction = -max;
		} else {
			correction = max;
		}
		return correction;
	}
	
}
