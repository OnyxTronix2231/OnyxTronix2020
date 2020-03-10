package robot.autonomous.commands;

import static robot.autonomous.AutonomousConstants.Paths.*;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.MoveToPose;

public class TestNinetyDegreePath extends SequentialCommandGroup {

  public TestNinetyDegreePath(final DriveTrain driveTrain){
    super(
        new MoveToPose(driveTrain, TEST_PATH_NINETY_DEGREES.getPoseAt(1)),
        new MoveToPose(driveTrain, TEST_PATH_NINETY_DEGREES.getPoseAt(2)),
        new MoveToPose(driveTrain, TEST_PATH_NINETY_DEGREES.getPoseAt(3)),
        new MoveToPose(driveTrain, TEST_PATH_NINETY_DEGREES.getPoseAt(4)),
        new MoveToPose(driveTrain, TEST_PATH_NINETY_DEGREES.getPoseAt(5)),
        new MoveToPose(driveTrain, TEST_PATH_NINETY_DEGREES.getPoseAt(6)));
  }
}
