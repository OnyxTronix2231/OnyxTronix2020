package robot.crossSubsystem.commands;

import static robot.crossSubsystem.CrossSubsystemConstants.DELAY_AFTER_SHOOT;
import static robot.crossSubsystem.CrossSubsystemConstants.DELAY_BEFORE_CHECK;
import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_DELAY;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.ballStopper.BallStopper;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.shooter.Shooter;
import robot.shooter.commands.WaitUntilShooterVelocityIsntOnTarget;
import robot.shooter.commands.WaitUntilShooterVelocityOnTarget;
import robot.storageConveyor.StorageConveyor;
import robot.yawControl.YawControl;
import vision.limelight.Limelight;

import java.util.function.DoubleSupplier;

public class MoveConveyorsByLoaderAsTriggerWithVision extends SequentialCommandGroup {

  public MoveConveyorsByLoaderAsTriggerWithVision(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                                  final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                                  final YawControl yawControl, final DoubleSupplier loaderSpeed,
                                                  final DoubleSupplier storageSpeedSupplier,
                                                  final DoubleSupplier ballStopperSpeedSupplier) {
    super(
        race(new WaitUntilShooterVelocityIsntOnTarget(shooter, 0),
            new WaitUntilWithCounter(shooter::isOnTarget).andThen(

                race(new WaitUntilBallIsNotInLoader(loaderConveyor),

                race(new WaitUntilCommand(() -> !Limelight.getInstance().targetFound()),
                    new WaitUntilWithCounter(() -> Limelight.getInstance().targetFound()).andThen(

                    race(new WaitUntilCommand(() -> !yawControl.isOnTarget()),
                    new WaitUntilWithCounter(yawControl::isOnTarget).andThen(

                    sequence(new WaitUntilShooterVelocityOnTarget(shooter, DELAY_AFTER_SHOOT),
                        new MoveLoaderConveyorBySpeed(loaderConveyor, loaderSpeed).
                            withTimeout(LOADER_DELAY))))))))),
//        deadline(new WaitUntilShooterVelocityOnTarget(shooter, DELAY_BEFORE_CHECK),
//            sequence(new WaitUntilShooterVelocityIsntOnTarget(shooter, DELAY_AFTER_SHOOT),
        new MoveConveyorsUntilBallInLoader(loaderConveyor, ballStopper, storageConveyor,
            loaderSpeed, storageSpeedSupplier,
            ballStopperSpeedSupplier));//));
  }

  @Override
  public boolean isFinished() {
    if(super.isFinished()) {
      initialize();
    }
    return false;
  }
}
