package org.usfirst.frc.team486.robot.RPi;

public class Packet {
	
	private boolean light;
	private boolean horizontal;
	private boolean vertical;
	private double percentage;
	private double value;
	
	public Packet(double val){
		this.value = val;
		double offset = 0;
		// light off, horizontal
		if ((val > 0) && (val < 50)){
			this.light = false;
			this.horizontal = true;
			this.vertical = false;
			offset = 0;
		// light off, vertical
		} else if ((val > 50) && (val < 100)){
			this.light = false;
			this.horizontal = false;
			this.vertical = true;
			offset = 50;
		// light on, horizontal
		} else if ((val > 100) && (val < 150)){
			this.light = true;
			this.horizontal = true;
			this.vertical = false;
			offset = 100;
		// light on, vertical
		} else if ((val > 150) && (val < 200)){
			this.light = true;
			this.horizontal = false;
			this.vertical = true;
			offset = 150;
		}
		this.percentage = this.calculate_percentage(val - offset);
	}
	
	private double calculate_percentage(double val){
		double new_val = val - 25.0; //centers at 0;
		double shifted = new_val/25.0;
		double percentage = shifted * 100.0;
		return percentage;
	}
	
	public boolean get_light(){
		return this.light;
	}
	
	public boolean get_horizontal(){
		return this.horizontal;
	}
	
	public boolean get_vertical(){
		return this.vertical;
	}
	
	public double get_percentage(){
		return this.percentage;
	}
	
	public double get_value(){
		return this.value;
	}
}
