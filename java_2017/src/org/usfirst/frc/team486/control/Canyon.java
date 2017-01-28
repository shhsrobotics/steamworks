package org.usfirst.frc.team486.control;

import java.util.HashMap;

import org.opencv.core.Point;

public class Canyon {
	
	private double K_THRESHOLD;
	private double K_BOUND;
	private double K_NEG_FLOOR;
	private double K_NEG_CIELING;
	private double K_POS_FLOOR;
	private double K_POS_CIELING;
	
	private Line neg;
	private Line pos;
	
	public Canyon(HashMap<String,Double> val_dict){
		this.K_BOUND = (double) val_dict.get("bound");
		this.K_THRESHOLD = (double) val_dict.get("threshold");
		this.K_NEG_CIELING = (double) val_dict.get("neg_cieling");
		this.K_NEG_FLOOR = (double) val_dict.get("neg_floor");
		this.K_POS_CIELING = (double) val_dict.get("pos_cieling");
		this.K_POS_FLOOR = (double) val_dict.get("pos_floor");
		
		Point neg_floor = new Point(this.K_THRESHOLD, this.K_NEG_FLOOR);
		Point neg_cieling = new Point(this.K_BOUND, this.K_NEG_CIELING);
		Point pos_floor = new Point(this.K_THRESHOLD, this.K_POS_FLOOR);
		Point pos_cieling = new Point(this.K_BOUND, this.K_POS_CIELING);
		
		this.neg = new Line(neg_floor, neg_cieling);
		this.pos = new Line(pos_floor, pos_cieling);
	}
	
	public double evaluate(double x){
		double output = 0;
		if (!((x > - this.K_THRESHOLD) && (x < this.K_THRESHOLD))) {
			// if x is not in threshold
			if (x > 0){
				// if x is positive
				if (x < this.K_BOUND){
					// if within bound
					output = this.pos.evaluate(x);
				} else {
					// if not within bound
					output = this.K_POS_CIELING;
				}
			} else {
				// if x is negative
				if (x > -this.K_BOUND){
					// if within bound
					output = (-1)*this.neg.evaluate(x);
				} else {
					// if not within bound
					output = (-1)*this.K_NEG_CIELING;
				}
			}
		}
		return output;
	}
}
