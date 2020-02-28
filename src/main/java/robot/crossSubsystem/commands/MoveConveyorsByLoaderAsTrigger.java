package robot.crossSubsystem.commands;

import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_DELAY;
import static robot.crossSubsystem.CrossSubsystemConstants.STORAGE_SPEED;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.ballStopper.BallStopper;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.shooter.Shooter;
import robot.shooter.commands.WaitUntilShooterVelocityIsNotOnTarget;
import robot.storageConveyor.StorageConveyor;
import robot.yawControl.YawControl;
import vision.limelight.Limelight;

public class MoveConveyorsByLoaderAsTrigger extends SequentialCommandGroup {

  public MoveConveyorsByLoaderAsTrigger(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                        final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                        final YawControl yawControl, boolean withVision) {
    super(
        race(new WaitUntilBallIsNotInLoader(loaderConveyor),
            race(new WaitUntilShooterVelocityIsNotOnTarget(shooter),
                race(new WaitUntilCommand(() -> withVision && !Limelight.getInstance().targetFound()),
                    race(new WaitUntilCommand(() -> !yawControl.isOnTarget()),
                        sequence(new WaitCommand(0.1),
                            (new MoveLoaderConveyorBySpeed(loaderConveyor, () -> LOADER_SPEED).
                                withTimeout(LOADER_DELAY))))))),

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
