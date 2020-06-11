package robot.autonomous;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.autonomous.commands.ResetPosition;
import robot.autonomous.commands.TargetToThreeBallsAtGeneratorToInitiationLinePath;
import robot.autonomous.commands.TestCollector;
import robot.autonomous.commands.TestSPath;
import robot.autonomous.commands.TestWeirdPath;
import robot.ballCollector.BallCollector;
import robot.ballStopper.BallStopper;
import robot.drivetrain.DriveTrain;
import robot.loaderConveyor.LoaderConveyor;
import robot.storageConveyor.StorageConveyor;

public class AutonomousOiBinder {

  public AutonomousOiBinder(final DriveTrain driveTrain, final Trigger testAutonomous, final Trigger resetPosition, final BallCollector ballCollector,
                            final LoaderConveyor loaderConveyor, final StorageConveyor storageConveyor, final BallStopper ballStopper) {
    testAutonomous.whileActiveOnce(new TestCollector(driveTrain, ballCollector,
        loaderConveyor, storageConveyor, ballStopper));
    resetPosition.whenActive(new ResetPosition(driveTrain));
  }
}

