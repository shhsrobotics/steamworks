package org.usfirst.frc.team486.robot.camera;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Display {
	
	Scalar red = new Scalar(0,0,255);
	Scalar green = new Scalar(0,255,0);
	Scalar blue = new Scalar(255,0,0);
	
	public Scalar select_color(String color_key){
		Scalar color = new Scalar(0,0,0);
		if (color_key == "red") {
			color = this.red;
		} else if (color_key == "green") {
			color = this.green;
		} else if (color_key == "blue") {
			color = this.blue;
		}
		return color;
	}
	
	public void draw_rectangle(Mat img, Point pt1, Point pt2, String color_key){
		Scalar color = this.select_color(color_key);
		Imgproc.rectangle(img, pt1, pt2, color);
	}
}
