package org.usfirst.frc.team486.robot.camera;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class Prep {
	
	//opencv uses ranges (H: 0-180, S: 0-255, V:0-255)
	Scalar lower_hsv = new Scalar(50,20,240);
	Scalar upper_hsv = new Scalar(90,255,255);
	
	public void filter_hsv(Mat hsv, Mat destination){
		Core.inRange(hsv, this.lower_hsv, this.upper_hsv, destination);
	}
}
