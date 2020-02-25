package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperConstants;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

public class MoveAllConveyors extends ParallelCommandGroup {

  public MoveAllConveyors(LoaderConveyor loaderConveyor, BallStopper ballStopper, StorageConveyor storageConveyor,
  final double loaderSpeed, final double storageSpeed, final double stopperSpeed) {
   super(new MoveBallStopperBySpeed(ballStopper ,() -> stopperSpeed, BallStopperConstants.DELAY),
       new MoveLoaderConveyorBySpeed(loaderConveyor, () -> loaderSpeed),
       new MoveStorageConveyorBySpeed(storageConveyor, () -> storageSpeed));
  }

}
