package org.usfirst.frc.team486.robot.camera;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Display {
	
	// ----------------------------------------------------------
	// DEFAULT COLOR DEFINITIONS
	// ----------------------------------------------------------
	private static final Scalar red = new Scalar(0,0,255);
	private static final Scalar green = new Scalar(0,255,0);
	private static final Scalar blue = new Scalar(255,0,0);
	
	// ----------------------------------------------------------
	// DEFAULT COLOR SELECTION METHOD
	// ----------------------------------------------------------
	private Scalar select_color(String color_key){
		Scalar color = new Scalar(0,0,0);
		if (color_key == "red") {
			color = Display.red;
		} else if (color_key == "green") {
			color = Display.green;
		} else if (color_key == "blue") {
			color = Display.blue;
		}
		return color;
	}
	
	// ----------------------------------------------------------
	// RECTANGLE DRAWING METHOD
	// ----------------------------------------------------------
	public void draw_rectangle(Mat img, Point pt1, Point pt2, String color_key){
		Scalar color = this.select_color(color_key);
		Imgproc.rectangle(img, pt1, pt2, color);
	}
	
	// ----------------------------------------------------------
	// POINT DRAWING METHOD
	// ----------------------------------------------------------
	public void draw_point(Mat img, Point pt, String color_key){
		Scalar color = this.select_color(color_key);
		Imgproc.circle(img, pt, 10, color, -1);
	}
}
