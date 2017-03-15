package org.usfirst.frc.team486.robot.triggers;

import org.usfirst.frc.team486.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class CloseTrigger extends Trigger {

    public boolean get() {
        return (Robot.oi.left_close.get() || Robot.oi.right_close.get());
    }
}
