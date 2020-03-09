package robot.ballTrigger.commands;

import static robot.ballTrigger.BallTriggerConstants.LOADER_SHOT_SPEED;
import static robot.ballTrigger.BallTriggerConstants.MAKE_A_SHOT_TIMEOUT;
import static robot.ballTrigger.BallTriggerConstants.WAIT_FOR_CONSISTENT_TARGET;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.ballStopper.BallStopper;
import robot.crossSubsystem.commands.MoveLoaderToShoot;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;

import java.util.function.BooleanSupplier;

public class ReleaseBallByLoaderAsTrigger extends SequentialCommandGroup {

  public ReleaseBallByLoaderAsTrigger(final LoaderConveyor loaderConveyor, final StorageConveyor storageConveyor,
                                      final BallStopper ballStopper, final BooleanSupplier canReleaseBallSupplier) {
    super(
        deadline(new WaitUntilCommand(() -> !canReleaseBallSupplier.getAsBoolean()),
            sequence(
                new WaitCommand(WAIT_FOR_CONSISTENT_TARGET),
                new MoveLoaderToShoot(loaderConveyor, ballStopper, storageConveyor))));
  }

  @Override
  public boolean isFinished() {
    if (super.isFinished()) {
      initialize();
    }
    return false;
  }
}
