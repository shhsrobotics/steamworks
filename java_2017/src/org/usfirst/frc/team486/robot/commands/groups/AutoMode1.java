package org.usfirst.frc.team486.robot.commands.groups;

import org.usfirst.frc.team486.robot.commands.AutoDriveDistance;
import org.usfirst.frc.team486.robot.commands.GrabGear;
import org.usfirst.frc.team486.robot.commands.LiftGear;
import org.usfirst.frc.team486.robot.commands.Turn;
import org.usfirst.frc.team486.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMode1 extends CommandGroup {

    public AutoMode1() {
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
        // arm.
    	addSequential(new LiftGear(false));
    	addSequential(new Wait(0.25));
    	addSequential(new GrabGear(true));
    	addSequential(new Wait(0.25));
    	addSequential(new LiftGear(true));
    	addSequential(new AutoDriveDistance(75.0, 0.4)); // This is the real-world value
    	addSequential(new GrabGear(false));
    	addSequential(new Wait(0.25));
    	addSequential(new AutoDriveDistance(-35.0, -0.4)); // This is the real-world value
    	addSequential(new Turn(-60.0));
    	addSequential(new AutoDriveDistance(83.0, 0.4)); // This is the real-world value
    }
}
