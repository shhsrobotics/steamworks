package org.usfirst.frc.team486.robot.camera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

public class Track2 {
	
	private int cut = 1;
	
	private int num_blobs = 0;
	
	private int min_x = -1;
	private int min_y = -1;
	private int max_x = -1;
	private int max_y = -1;
	
	private int height = 0;
	private int width = 0;
	
	private boolean found = false;
	
	public List<MatOfPoint> find_blobs(Mat filtered) {
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat heirarchy = new Mat();
		Imgproc.findContours(filtered, contours, heirarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		return contours;
	}
	
	public Point find_center(List<MatOfPoint> contours){
		for (Iterator<MatOfPoint> iter = contours.iterator(); iter.hasNext(); ) {
		    MatOfPoint contour = iter.next();
		    if (Imgproc.contourArea(contour) > 300){
		    	this.num_blobs = this.num_blobs + 1;
		    	Rect rect = Imgproc.boundingRect(contour);
		    	if (this.min_x == -1){
		    		this.min_x = rect.x;
		    	} else {
		    		if (rect.x < this.min_x){
		    			this.min_x = rect.x;
		    		}
		    	}
		    	if (this.min_y == -1){
		    		this.min_y = rect.y;
		    	} else {
		    		if (rect.y < this.min_y){
		    			this.min_y = rect.y;
		    		}
		    	}
		    	if (this.max_x == -1){
		    		this.max_x = rect.x + rect.width;
		    	} else {
		    		if (rect.x + rect.width > this.max_x){
		    			this.max_x = rect.x + rect.width;
		    		}
		    	}
		    	if (this.max_y == -1){
		    		this.max_y = rect.y + rect.height;
		    	} else {
		    		if (rect.y + rect.height > this.max_y){
		    			this.max_y = rect.y + rect.height;
		    		}
		    	}
		    }
		}
		Point out = new Point(160,120);
		this.width = this.max_x - this.min_x;
		this.height = this.max_y - this.min_y;
		if (this.num_blobs >= this.cut){
    		out = this.find_center_from_dimensions(this.min_x, this.min_y, this.width, this.height);
    		this.found = true;
    	} else {
    		this.found = false;
    	}
		this.reset();
		return out;
	}
	
	private Point find_center_from_dimensions(double x, double y, double w, double h){
		double center_x = 0;
		double center_y = 0;
		
		center_x = x + w * 0.5;
		center_y = y + h * 0.5;
		
		return new Point(center_x, center_y);
	}
	
	public void reset(){
		this.num_blobs = 0;
		
		this.min_x = -1;
		this.min_y = -1;
		this.max_x = -1;
		this.max_y = -1;
		
		this.width = 0;
		this.height = 0;
	}
	
	public boolean get_found(){
		return this.found;
	}
}
