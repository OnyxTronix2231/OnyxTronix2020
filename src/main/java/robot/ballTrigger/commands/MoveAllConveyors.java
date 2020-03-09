package robot.ballTrigger.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballStopper.BallStopper;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

import java.util.function.DoubleSupplier;

public class MoveAllConveyors extends ParallelCommandGroup {

  public MoveAllConveyors(LoaderConveyor loaderConveyor, StorageConveyor storageConveyor, BallStopper ballStopper,
                          final DoubleSupplier loaderSpeed, final DoubleSupplier storageSpeed, final DoubleSupplier
                              stopperSpeed) {
   super(new MoveBallStopperBySpeed(ballStopper, stopperSpeed),
       new MoveLoaderConveyorBySpeed(loaderConveyor, loaderSpeed),
       new MoveStorageConveyorBySpeed(storageConveyor, storageSpeed));
  }

}
