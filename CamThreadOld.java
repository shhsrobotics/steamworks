package org.usfirst.frc.team486.robot;

import java.util.HashMap;

import org.opencv.core.Point;
import org.usfirst.frc.team486.control.Line;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class CamThread {



	// ----------------------------------------------------------
	// FILTER
	// ----------------------------------------------------------
	public static String FILTER_COLOR = "green";



	// ----------------------------------------------------------
	// POWER DISTRIBUTION PANEL
	// ----------------------------------------------------------
	private static PowerDistributionPanel pdp = new PowerDistributionPanel();


	// ----------------------------------------------------------
	// VOLTAGE CONSTANTS (NEG)
	// ----------------------------------------------------------

	// MEASUREMENTS
	private static final double V_LOW_NEG = 11.9;
	private static final double V_LOW_K_CIELING_NEG= 0.4;
	private static final double V_LOW_K_FLOOR_NEG = 0.37;
	private static final double V_HIGH_NEG = 12.7;
	private static final double V_HIGH_K_CIELING_NEG = 0.37;
	private static final double V_HIGH_K_FLOOR_NEG = 0.35;

	// CONSTRUCTS
	private static final Point V_P1_FLOOR_NEG = new Point(V_LOW_NEG, V_LOW_K_FLOOR_NEG);
	private static final Point V_P2_FLOOR_NEG = new Point(V_HIGH_NEG, V_HIGH_K_FLOOR_NEG);
	private static final Point V_P1_CIELING_NEG = new Point(V_LOW_NEG, V_LOW_K_CIELING_NEG);
	private static final Point V_P2_CIELING_NEG = new Point(V_HIGH_NEG, V_HIGH_K_CIELING_NEG);
	private static final Line V_to_NEG_FLOOR = new Line(V_P1_FLOOR_NEG, V_P2_FLOOR_NEG);
	private static final Line V_to_NEG_CIELING= new Line(V_P1_CIELING_NEG, V_P2_CIELING_NEG);



	// ----------------------------------------------------------
	// VOLTAGE CONSTANTS (POS)
	// ----------------------------------------------------------

	// MEASUREMENTS
	private static final double V_LOW_POS = 11.9;
	private static final double V_LOW_K_CIELING_POS = 0.4;
	private static final double V_LOW_K_FLOOR_POS = 0.37;
	private static final double V_HIGH_POS = 12.7;
	private static final double V_HIGH_K_CIELING_POS = 0.37;
	private static final double V_HIGH_K_FLOOR_POS = 0.35;

	// CONSTRUCTS
	private static final Point V_P1_FLOOR_POS = new Point(V_LOW_POS, V_LOW_K_FLOOR_POS);
	private static final Point V_P2_FLOOR_POS = new Point(V_HIGH_POS, V_HIGH_K_FLOOR_POS);
	private static final Point V_P1_CEILING_POS = new Point(V_LOW_POS, V_LOW_K_CIELING_POS);
	private static final Point V_P2_CEILING_POS = new Point(V_HIGH_POS, V_HIGH_K_CIELING_POS);
	private static final Line V_to_POS_FLOOR = new Line(V_P1_FLOOR_POS, V_P2_FLOOR_POS);
	private static final Line V_to_POS_CIELING = new Line(V_P1_CEILING_POS, V_P2_CEILING_POS);



	public static HashMap<String,Double> COOK_SETTINGS= new HashMap<String,Double>();
	private static double K_BOUND = 0.05;
	private static double K_THRESHOLD = 0.5;
	private static double K_NEG_FLOOR = V_to_NEG_FLOOR.evaluate(pdp.getVoltage());
	private static double K_NEG_CIELING = V_to_NEG_CIELING.evaluate(pdp.getVoltage());
	private static double K_POS_FLOOR = V_to_POS_FLOOR.evaluate(pdp.getVoltage());
	private static double K_POS_CIELING = V_to_POS_CIELING.evaluate(pdp.getVoltage());

	public static double correction = 0;

	// WARNING WARNING WARNING
	public static void init(){
		COOK_SETTINGS.put("bound", K_BOUND);
		COOK_SETTINGS.put("threshold", K_THRESHOLD);
		COOK_SETTINGS.put("neg_floor", K_NEG_FLOOR);
		COOK_SETTINGS.put("neg_cieling", K_NEG_CIELING);
		COOK_SETTINGS.put("pos_floor", K_POS_FLOOR);
		COOK_SETTINGS.put("pos_cieling", K_POS_CIELING);
	}

	public void update_cook_settings(){
		double voltage = pdp.getVoltage();
		K_NEG_FLOOR = V_to_NEG_FLOOR.evaluate(voltage);
		K_NEG_CIELING = V_to_NEG_CIELING.evaluate(voltage);
		K_POS_FLOOR = V_to_POS_FLOOR.evaluate(voltage);
		K_POS_CIELING = V_to_POS_CIELING.evaluate(voltage);
	}
}
