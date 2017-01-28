package org.usfirst.frc.team486.robot.triggers;

import org.usfirst.frc.team486.robot.Robot;
import org.usfirst.frc.team486.robot.RobotMap;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class OpstickBackTrigger extends Trigger {

    public boolean get() {
        return (Robot.oi.opstick.getY() < (-RobotMap.OPSTICK_THRESHOLD));
    }
}
