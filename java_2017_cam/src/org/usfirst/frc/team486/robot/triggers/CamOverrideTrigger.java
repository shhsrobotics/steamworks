package org.usfirst.frc.team486.robot.triggers;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class CamOverrideTrigger extends Trigger {

    public boolean get() {
        return (Robot.oi.left_6.get()   |
        		Robot.oi.left_7.get()   |
        		Robot.oi.left_8.get()   | 
        		Robot.oi.left_9.get()   |
        		Robot.oi.left_10.get()  |
        		Robot.oi.left_11.get()  |
        		Robot.oi.right_6.get()  | 
        		Robot.oi.right_7.get()  | 
        		Robot.oi.right_8.get()  | 
        		Robot.oi.right_9.get()  |
        		Robot.oi.right_10.get() |
        		Robot.oi.right_11.get() );
    }
}
