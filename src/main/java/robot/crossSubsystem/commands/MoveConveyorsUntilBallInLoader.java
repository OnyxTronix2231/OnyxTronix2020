package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import robot.ballStopper.BallStopper;
import robot.loaderConveyor.LoaderConveyor;
import robot.storageConveyor.StorageConveyor;

public class MoveConveyorsUntilBallInLoader extends ParallelDeadlineGroup {

  public MoveConveyorsUntilBallInLoader(LoaderConveyor loaderConveyor, BallStopper ballStopper,
                                        StorageConveyor storageConveyor,
                                        final double loaderSpeed, final double storageSpeed, final double stopperSpeed) {
    super(new WaitUntilBallInLoader(loaderConveyor), new MoveAllConveyors(loaderConveyor, ballStopper, storageConveyor,
    loaderSpeed, storageSpeed, stopperSpeed));
  }
}
