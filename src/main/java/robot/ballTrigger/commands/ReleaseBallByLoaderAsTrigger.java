package robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.ballStopper.BallStopper;
import robot.crossSubsystem.commands.MoveConveyorsBeforeShoot;
import robot.crossSubsystem.commands.MoveLoaderToShoot;
import robot.loaderConveyor.LoaderConveyor;
import robot.storageConveyor.StorageConveyor;

import java.util.function.BooleanSupplier;

public class ReleaseBallByLoaderAsTrigger extends SequentialCommandGroup {

  public ReleaseBallByLoaderAsTrigger(final LoaderConveyor loaderConveyor, final StorageConveyor storageConveyor,
                                      final BallStopper ballStopper, final BooleanSupplier canReleaseBallSupplier) {
    super(
        race(new WaitUntilCommand(() -> !canReleaseBallSupplier.getAsBoolean()),
            sequence(
                new WaitUntilCommand(canReleaseBallSupplier),
                new MoveConveyorsBeforeShoot(loaderConveyor, ballStopper, storageConveyor))),
        deadline(new WaitUntilCommand(() -> !canReleaseBallSupplier.getAsBoolean()),
            sequence(
                new WaitUntilCommand(canReleaseBallSupplier),
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
