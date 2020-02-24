package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballStopper.BallStopper;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;

public class MoveConveyorsUntilBallInLoader extends SequentialCommandGroup {

  public MoveConveyorsUntilBallInLoader(LoaderConveyor loaderConveyor, BallStopper ballStopper,
                                        StorageConveyor storageConveyor,
                                        final double loaderSpeed, final double storageSpeed, final double stopperSpeed) {
    super(deadline(new WaitUntilBallInLoader(loaderConveyor), new MoveAllConveyors(loaderConveyor, ballStopper, storageConveyor,
    loaderSpeed, storageSpeed, stopperSpeed)), new WaitUntilBallIsNotInLoader(loaderConveyor),
        new MoveLoaderConveyorBySpeed(loaderConveyor, () -> -0.3).
        withTimeout(0.2));
  }
}
