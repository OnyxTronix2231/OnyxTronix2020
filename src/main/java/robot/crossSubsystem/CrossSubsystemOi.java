package robot.crossSubsystem;

import static robot.crossSubsystem.CrossSubsystemConstants.MAX_VELOCITY;

import edu.wpi.first.wpilibj.XboxController;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.BallStopper.BallStopper;
import robot.LoaderConveyor.LoaderConveyor;
import robot.LoaderConveyor.commands.MoveLoaderByVelocity;
import robot.StorageConveyor.StorageConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByVelocity;

import java.util.function.DoubleSupplier;

public class CrossSubsystemOi {

  public CrossSubsystemOi(final UniqueAxisCache buttonJoystickAxisCache,
                          final Shooter shooter, final LoaderConveyor loaderConveyor,
                          final StorageConveyor storageConveyor,final BallStopper ballStopper) {
    final JoystickAxis shootByVelocityAxis =
            buttonJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);
    shootByVelocityAxis.whileActiveContinuous(new ShootWithBallStopperTrigger(shooter, loaderConveyor, storageConveyor,
            ballStopper, ShooterVELOCITY, storageSpeedSupplier)
  }
}
