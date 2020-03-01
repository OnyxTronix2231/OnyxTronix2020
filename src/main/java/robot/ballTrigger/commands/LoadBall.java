package robot.ballTrigger.commands;

import static robot.ballTrigger.BallTriggerConstants.BALL_STOPPER_SPEED;
import static robot.ballTrigger.BallTriggerConstants.DELAY_UNTIL_BALL_LOADING_ENDS;
import static robot.ballTrigger.BallTriggerConstants.LOADER_RETURN_BALL_DOWN_SPEED;
import static robot.ballTrigger.BallTriggerConstants.LOADER_RETURN_BALL_TIMEOUT;
import static robot.ballTrigger.BallTriggerConstants.LOADER_SPEED;
import static robot.ballTrigger.BallTriggerConstants.STORAGE_SPEED;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.ballStopper.BallStopper;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;

public class LoadBall extends SequentialCommandGroup {

  public LoadBall(final LoaderConveyor loaderConveyor, final StorageConveyor storageConveyor,
                  final BallStopper ballStopper) {
    super(
        deadline(
            new WaitUntilCommand(loaderConveyor::isBallInLoader),
            new MoveAllConveyors(loaderConveyor, storageConveyor, ballStopper,
                () -> LOADER_SPEED, () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED)),
        new WaitCommand(DELAY_UNTIL_BALL_LOADING_ENDS),
        race(
            new WaitUntilCommand(loaderConveyor::isBallInLoader),
            new MoveLoaderConveyorBySpeed(loaderConveyor, () -> LOADER_RETURN_BALL_DOWN_SPEED).
                withTimeout(LOADER_RETURN_BALL_TIMEOUT)));
  }
}
