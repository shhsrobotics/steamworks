package org.usfirst.frc.team486.robot.camera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Point;
import org.opencv.core.Rect;

public class Track {
	
	public int num_blobs = 0;
	public Point center;
	public Point pt1; //specify whether left or right, top or bottom
	public Point pt2; //specify whether left or right, top or bottom
	public int width = 0;
	public int height = 0;
	
	public void track(Mat filtered) {
		List<MatOfPoint> contours = this.find_blobs(filtered);
		this.find_dimensions(contours);
	}
	
	private List<MatOfPoint> find_blobs(Mat filtered) {
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat heirarchy = new Mat();
		Imgproc.findContours(filtered, contours, heirarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		return contours;
	}
	
	private void find_dimensions(List<MatOfPoint> contours){
		
		int min_x = (Integer) null;
		int min_y = (Integer) null;
		int max_x = (Integer) null;
		int max_y = (Integer) null;
		
		for (Iterator<MatOfPoint> iter = contours.iterator(); iter.hasNext(); ) {
		    MatOfPoint contour = iter.next();
		    if (Imgproc.contourArea(contour) > 300){
		    	this.num_blobs = this.num_blobs + 1;
		    	Rect rect = Imgproc.boundingRect(contour);
		    	if (min_x == (Integer) null){
		    		min_x = rect.x;
		    	} else {
		    		if (rect.x < min_x){
		    			min_x = rect.x;
		    		}
		    	}
		    	if (min_y == (Integer) null){
		    		min_y = rect.y;
		    	} else {
		    		if (rect.y < min_y){
		    			min_y = rect.y;
		    		}
		    	}
		    	if (max_x == (Integer) null){
		    		max_x = rect.x + rect.width;
		    	} else {
		    		if (rect.x + rect.width > max_x){
		    			max_x = rect.x + rect.width;
		    		}
		    	}
		    	if (max_y == (Integer) null){
		    		max_y = rect.y + rect.height;
		    	} else {
		    		if (rect.y + rect.height > max_y){
		    			max_y = rect.y + rect.height;
		    		}
		    	}
		    }
		}
		
		this.pt1 = new Point(min_x, min_y);
		this.pt2 = new Point(max_x, max_y);
		this.width = (int) (this.pt2.x- this.pt1.x);
		this.height = (int) (this.pt2.y- this.pt1.y);
		this.center = new Point((int) (this.pt1.x + this.width*0.5), (int) (this.pt1.y + this.height*0.5));
	}

}
