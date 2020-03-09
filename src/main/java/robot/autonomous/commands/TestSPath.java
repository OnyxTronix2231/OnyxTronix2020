package robot.autonomous.commands;

import static robot.autonomous.AutonomousConstants.*;
import static robot.autonomous.AutonomousConstants.Paths.*;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.autonomous.AutonomousConstants;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.MoveToPose;

public class TestSPath extends SequentialCommandGroup {

  public TestSPath(final DriveTrain driveTrain){
    super(
        new MoveToPose(driveTrain, testPathS.getPoseAt(1)),
        new MoveToPose(driveTrain, testPathS.getPoseAt(2)),
        new MoveToPose(driveTrain, testPathS.getPoseAt(3)),
        new MoveToPose(driveTrain, testPathS.getPoseAt(4)),
        new MoveToPose(driveTrain, testPathS.getPoseAt(5)),
        new MoveToPose(driveTrain, testPathS.getPoseAt(6))
    );
  }
}
