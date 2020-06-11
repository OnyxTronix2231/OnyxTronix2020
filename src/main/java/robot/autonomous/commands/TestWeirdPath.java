package robot.autonomous.commands;

import static robot.autonomous.AutonomousConstants.Paths.TEST_ARROW_PATH;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.MoveToPose;

public class TestWeirdPath extends SequentialCommandGroup {

  public TestWeirdPath(final DriveTrain driveTrain) {
    super(
        new MoveToPose(driveTrain, TEST_ARROW_PATH.getPoseAt(1)),
        new MoveToPose(driveTrain, TEST_ARROW_PATH.getPoseAt(2)),
        new MoveToPose(driveTrain, TEST_ARROW_PATH.getPoseAt(3)),
        new MoveToPose(driveTrain, TEST_ARROW_PATH.getPoseAt(4))
    );
  }
}