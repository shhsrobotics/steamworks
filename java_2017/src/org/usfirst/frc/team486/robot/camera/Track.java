package org.usfirst.frc.team486.robot.camera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team486.control.Canyon;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

import org.opencv.core.Point;
import org.opencv.core.Rect;

public class Track {
	
	// ----------------------------------------------------------
	// TRACK INSTANCE COMPUTATION VARIABLES
	// ----------------------------------------------------------
	private int num_blobs = 0;
	
	private int min_x = -1;
	private int min_y = -1;
	private int max_x = -1;
	private int max_y = -1;
	
	private int height = 0;
	private int width = 0;
	
	private boolean found = false;
	
	// ----------------------------------------------------------
	// TRACK INSTANCE COMPUTATION CONSTANTS
	// ----------------------------------------------------------
	private static final Point IMG_CENTER = new Point(320,220);
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 220;
	private static final int CUT = 1;
	
	// ----------------------------------------------------------
	// CONTOUR FINDING METHOD
	// ----------------------------------------------------------
	public List<MatOfPoint> find_blobs(Mat filtered) {
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat heirarchy = new Mat();
		Imgproc.findContours(filtered, contours, heirarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		return contours;
	}
	
	// ----------------------------------------------------------
	// DIMENSION FINDING METHOD (calculates relative positions)
	// ----------------------------------------------------------
	public Status find_center(List<MatOfPoint> contours){
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
		this.width = this.max_x - this.min_x;
		this.height = this.max_y - this.min_y;
		if (this.num_blobs >= Track.CUT){
    		this.found = true;
    	} else {
    		this.found = false;
    	}
		Point center = this.dim_to_center(this.min_x, this.min_y, this.width, this.height);
		double distance = this.find_distance();
		double offset = this.find_offset();
		double correction = this.find_correction();
		this.reset();
		return new Status(center, distance, offset, correction);
	}
	
	public Point dim_to_center(double min_x, double min_y, double w, double h){
		Point out = new Point(0,0);
		if (this.found){
			out.x = min_x + w * 0.5;
			out.y = min_y + w * 0.5;
		}
		return out;
	}
	
	// ----------------------------------------------------------
	// INSTANCE RESETING METHOD
	// ----------------------------------------------------------
	public void reset(){
		this.num_blobs = 0;
		
		this.min_x = -1;
		this.min_y = -1;
		this.max_x = -1;
		this.max_y = -1;
		
		this.width = 0;
		this.height = 0;
	}
	
	private double find_offset(){
		//yet to be implemented
		return 0.0;
	}
	
	private double find_distance(){
		//yet to be implemented
		return 0.0;
	}
	
	public boolean get_found(){
		return this.found;
	}
	
	private double find_correction(){
		// yet to be implemented
		return 0.0;
	}

}
