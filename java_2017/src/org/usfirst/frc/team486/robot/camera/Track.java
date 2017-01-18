package org.usfirst.frc.team486.robot.camera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Point;
import org.opencv.core.Rect;

public class Track {
	
	private int num_blobs = 0;
	private Point center;
	private Point pt1; //specify whether left or right, top or bottom
	private Point pt2; //specify whether left or right, top or bottom
	private int width = 0;
	private int height = 0;
	
	private int min_x = -1;
	private int min_y = -1;
	private int max_x = -1;
	private int max_y = -1;
	
	public void track(Mat filtered) {
		List<MatOfPoint> contours = this.find_blobs(filtered);
		this.find_dimensions(contours);
	}
	
	public List<MatOfPoint> find_blobs(Mat filtered) {
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat heirarchy = new Mat();
		Imgproc.findContours(filtered, contours, heirarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		return contours;
	}
	
	public void find_dimensions(List<MatOfPoint> contours){
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
	}
	
	public void construct_points(){
		this.pt1 = new Point(this.min_x, this.min_y);
		this.pt2 = new Point(this.max_x, this.max_y);
		this.width = (int) (this.pt2.x- this.pt1.x);
		this.height = (int) (this.pt2.y- this.pt1.y);
		this.center = new Point((int) (this.pt1.x + this.width*0.5), (int) (this.pt1.y + this.height*0.5));
	}
	
	public Point get_pt1(){
		return this.pt1;
	}
	
	public Point get_pt2(){
		return this.pt1;
	}
	
	public Point get_center(){
		return this.center;
	}

	public int get_num_blobs(){
		return this.num_blobs;
	}
	
	public int get_width(){
		return this.width;
	}
	
	public int get_height(){
		return this.height;
	}
}
