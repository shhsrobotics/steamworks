package org.usfirst.frc.team486.robot.camera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

import org.opencv.core.Point;
import org.opencv.core.Rect;

public class Track {
	
	private static PowerDistributionPanel pdp = new PowerDistributionPanel();
	
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
	// VOLTAGE CONSTANTS (left)
	// ----------------------------------------------------------
	private static final double V_LOW_LEFT = 11.9;
	private static final double V_LOW_K_CIELING_LEFT= 0.4;
	private static final double V_HIGH_LEFT = 12.7;
	private static final double V_HIGH_K_CIELING_LEFT = 0.37;
	private static final double V_SLOPE_LEFT = 
			(V_HIGH_K_CIELING_LEFT - V_LOW_K_CIELING_LEFT) / (V_HIGH_LEFT - V_LOW_LEFT);
	private static final double V_INTERCEPT_LEFT = V_LOW_K_CIELING_LEFT - (V_SLOPE_LEFT * V_LOW_LEFT);
	
	// ----------------------------------------------------------
	// VOLTAGE CONSTANTS (right)
	// ----------------------------------------------------------
	private static final double V_LOW_RIGHT = 11.9;
	private static final double V_LOW_K_CIELING_RIGHT = 0.4;
	private static final double V_HIGH_RIGHT = 12.7;
	private static final double V_HIGH_K_CIELING_RIGHT = 0.37;
	private static final double V_SLOPE_RIGHT = 
			(V_HIGH_K_CIELING_RIGHT - V_LOW_K_CIELING_RIGHT) / (V_HIGH_RIGHT - V_LOW_RIGHT);
	private static final double V_INTERCEPT_RIGHT = V_LOW_K_CIELING_RIGHT - (V_SLOPE_RIGHT * V_LOW_RIGHT);
	
	// ----------------------------------------------------------
	// "GLOBAL" VARIABLES
	// ----------------------------------------------------------
	private static Point center = new Point(320, 220);
	private static double correction = 0; //value between -1 and 1 (-1 being 100% left, 100% right)
	private static double offset = 0;
	private static double voltage = Track.pdp.getVoltage(); //first guess
	
	// ----------------------------------------------------------
	// TRACK INSTANCE COMPUTATION CONSTANTS
	// ----------------------------------------------------------
	private static final Point IMG_CENTER = new Point(320,220);
	private static final int IMG_WIDTH = 640;
	private static final int IMG_HEIGHT = 320;
	private static final int CUT = 1;
	
	// ----------------------------------------------------------
	// CORRECTION COMPUTATION CONSTANTS (all)
	// ----------------------------------------------------------
	private static final double K_THRESH = 0.05;
	private static final double K_BOUND = 0.5;
	private static final double K_DELTA = Track.K_THRESH-Track.K_BOUND;
	
	// ----------------------------------------------------------
	// CORRECTION COMPUTATION CONSTANTS (left)
	// ----------------------------------------------------------
	private static double K_LEFT_CIELING = 0.37;
	private static double K_LEFT_FLOOR = 0.35;
	private static double K_LEFT_SLOPE = 
			(Track.K_LEFT_CIELING - Track.K_LEFT_FLOOR)/Track.K_DELTA;
	private static double K_LEFT_INTERCEPT = 
			(Track.K_LEFT_FLOOR) - (Track.K_LEFT_SLOPE * Track.K_THRESH);
	
	// ----------------------------------------------------------
	// CORRECTION COMPUTATION CONSTANTS (right)
	// ----------------------------------------------------------
	private static double K_RIGHT_CIELING = 0.37;
	private static double K_RIGHT_FLOOR = 0.35;
	private static double K_RIGHT_SLOPE = 
			(Track.K_RIGHT_CIELING - Track.K_RIGHT_FLOOR)/Track.K_DELTA;
	private static double K_RIGHT_INTERCEPT = 
			(Track.K_RIGHT_FLOOR) - (Track.K_RIGHT_SLOPE * Track.K_THRESH);
	
	// ----------------------------------------------------------
	// UPDATE CONSTANTS (left)(right)
	// ----------------------------------------------------------
	private static void update_correction_constants(){
		Track.K_LEFT_CIELING = Track.V_SLOPE_LEFT*(Track.voltage) + Track.V_INTERCEPT_LEFT;
		Track.K_RIGHT_CIELING = Track.V_SLOPE_RIGHT*(Track.voltage) + Track.V_INTERCEPT_RIGHT;
		Track.K_LEFT_FLOOR = Track.K_LEFT_FLOOR - 0.02;
		Track.K_RIGHT_FLOOR = Track.K_RIGHT_FLOOR - 0.02;
		Track.K_LEFT_SLOPE = 
				(Track.K_LEFT_CIELING - Track.K_LEFT_FLOOR)/Track.K_DELTA;
		Track.K_LEFT_INTERCEPT = 
				(Track.K_LEFT_FLOOR) - (Track.K_LEFT_SLOPE * Track.K_THRESH);
		Track.K_RIGHT_SLOPE = 
				(Track.K_RIGHT_CIELING - Track.K_RIGHT_FLOOR)/Track.K_DELTA;
		Track.K_RIGHT_INTERCEPT = 
				(Track.K_RIGHT_FLOOR) - (Track.K_RIGHT_SLOPE * Track.K_THRESH);
	}
	
	
	// ----------------------------------------------------------
	// GENERAL TRACKING METHOD (finds contours and then filters)
	// ----------------------------------------------------------
	public void track(Mat filtered) {
		List<MatOfPoint> contours = this.find_blobs(filtered);
		this.find_dimensions(contours);
	}
	
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
		this.width = this.max_x - this.min_x;
		this.height = this.max_y - this.min_y;
		if (this.num_blobs >= Track.CUT){
    		this.found = true;
    	}
		Track.update(this.min_x, this.min_y, this.width, this.height);
		Track.update_correction_constants();
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
		
		this.found = false;
	}
	
	// ----------------------------------------------------------
	// RETURNS THE CENTER (static)
	// ----------------------------------------------------------
	public static Point get_center(){
		return Track.center;
	}

	// ----------------------------------------------------------
	// RETURNS THE DRIVE CORRECTION (static)
	// ----------------------------------------------------------
	public static double get_correction(){
		return Track.correction;
	}
	
	// ----------------------------------------------------------
	// RETURNS THE DRIVE OFFSET (static)
	// ----------------------------------------------------------
	public static double get_offset(){
		return Track.offset;
	}
	
	// ----------------------------------------------------------
	// UPDATES STATIC VARIABLES
	// ----------------------------------------------------------
	public static void update(int x, int y, int width, int height){
		Track.center.x = (int) (x + width*0.5);
		Track.center.y = (int) (y + height*0.5);
		Track.offset = (double) 2*((Track.center.x - Track.IMG_CENTER.x)/Track.IMG_WIDTH);
		Track.correction = Track.calc_correction(Track.offset);
		Track.voltage = Track.pdp.getVoltage();
	}
	
	// ----------------------------------------------------------
	// CALCULATES CORRECTION FROM OFFSET
	// ----------------------------------------------------------
	private static double calc_correction(double offset){
		double correction = 0;
		if ((-Track.K_THRESH <= offset) && (Track.K_THRESH >= offset)){
			//INSIDE THRESHOLD
			correction = 0;
		} else{
			//OUTSIDE THRESHOLD
			if (offset > 0){
				//RIGHT
				if (offset <= Track.K_BOUND){
					//SLOPED
					correction = Track.K_RIGHT_SLOPE*offset + Track.K_RIGHT_INTERCEPT;
				} else {
					//PLATEUD
					correction = Track.K_RIGHT_CIELING;
				}
			} else {
				//LEFT
				if (offset >= -Track.K_BOUND){
					//SLOPED
					correction = -(Track.K_LEFT_SLOPE*offset + Track.K_LEFT_INTERCEPT);
				} else {
					//PLATEUD
					correction = -(Track.K_LEFT_CIELING);
				}
			}
		}
		return correction;
	}
	
	// ----------------------------------------------------------
	// RETURNS THE NUMBER OF BLOBS IN THE INSTANCE
	// ----------------------------------------------------------
	public int get_num_blobs(){
		return this.num_blobs;
	}
	
	// ----------------------------------------------------------
	// RETURNS THE FOUND BOOLEAN IN THE INSTANCE
	// ----------------------------------------------------------
	public boolean get_found(){
		return this.found;
	}
	
}
