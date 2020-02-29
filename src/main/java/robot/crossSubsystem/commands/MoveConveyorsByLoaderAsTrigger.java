package robot.crossSubsystem.commands;

import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_SHOT_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.MAKE_A_SHOT_TIMEOUT;
import static robot.crossSubsystem.CrossSubsystemConstants.WAIT_FOR_CONSISTENT_TARGET;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.ballStopper.BallStopper;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.shooter.Shooter;
import robot.storageConveyor.StorageConveyor;
import robot.yawControl.YawControl;
import vision.limelight.Limelight;

public class MoveConveyorsByLoaderAsTrigger extends SequentialCommandGroup {

  public MoveConveyorsByLoaderAsTrigger(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                        final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                        final YawControl yawControl, boolean withVision) {
    super(
        race(new WaitUntilCommand(() -> !loaderConveyor.isBallInLoader()),
            race(new WaitUntilCommand(() -> !shooter.isOnTarget()),
                race(new WaitUntilCommand(() -> !Limelight.getInstance().targetFound() && withVision),
                    race(new WaitUntilCommand(() -> !yawControl.isOnTarget()),
                        sequence(new WaitCommand(WAIT_FOR_CONSISTENT_TARGET),
                            (new MoveLoaderConveyorBySpeed(loaderConveyor, () -> LOADER_SHOT_SPEED).
                                withTimeout(MAKE_A_SHOT_TIMEOUT))))))),

        new MoveConveyorsUntilBallInLoader(loaderConveyor, ballStopper, storageConveyor));
  }

  @Override
  public boolean isFinished() {
    if (super.isFinished()) {
      initialize();
    }
    return false;
  }
}
