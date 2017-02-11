package org.usfirst.frc.team486.robot.triggers;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class Opstick_1_3_Trigger extends Trigger {

    public boolean get() {
        return ((Robot.oi.shootoveride.get()) && (Robot.oi.trackbutton.get()));
    }
}
