package org.usfirst.frc.team486.robot.commands.groups;

import org.usfirst.frc.team486.robot.RobotMap;
import org.usfirst.frc.team486.robot.commands.AutoDriveDistance;
import org.usfirst.frc.team486.robot.commands.AutoShootPID;
import org.usfirst.frc.team486.robot.commands.GrabGear;
import org.usfirst.frc.team486.robot.commands.LiftGear;
import org.usfirst.frc.team486.robot.commands.Open;
import org.usfirst.frc.team486.robot.commands.Turn;
import org.usfirst.frc.team486.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterShootBlue extends CommandGroup {

    public AutoCenterShootBlue() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.s
    	addSequential(new GrabGear(true)); // Grab the gear
    	addSequential(new Wait(0.25)); // Wait for the previous command to complete
    	addSequential(new LiftGear(true)); // Lift the gear-grabber
    	addSequential(new AutoDriveDistance(50.0, RobotMap.AUTO_MOVE_SPEED)); // Move toward the pin
    	addSequential(new AutoDriveDistance(23.0, RobotMap.AUTO_MOVE_SLOW_SPEED)); // Slow down to place the gear
    	addSequential(new GrabGear(false)); // Release the gear
    	addSequential(new Wait(0.25)); // Wait for the previous command to complete
    	addSequential(new AutoDriveDistance(-35.0, -1 * RobotMap.AUTO_MOVE_SPEED)); // Move away from the pin
    	addSequential(new Turn(-90, RobotMap.TURN_SLOW_SPEED)); // Turn towards the boiler
    	addSequential(new AutoDriveDistance(136.0, RobotMap.AUTO_MOVE_SPEED));
    	addSequential(new Turn(-45, RobotMap.TURN_SLOW_SPEED));
    	addSequential(new AutoDriveDistance(32.0,RobotMap.AUTO_MOVE_SPEED));
    }
}
