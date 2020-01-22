package robot.crossSubsystem;

import static robot.crossSubsystem.CrossSubsystemConstants.MAX_VELOCITY;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.BallStopper.BallStopper;
import robot.LoaderConveyor.LoaderConveyor;
import robot.StorageConveyor.StorageConveyor;
import robot.shooter.Shooter;

import static robot.crossSubsystem.CrossSubsystemConstants.*;

public class CrossSubsystemOi {

  public CrossSubsystemOi(final UniqueAxisCache buttonJoystickAxisCache,
                          final Shooter shooter, final LoaderConveyor loaderConveyor,
                          final StorageConveyor storageConveyor,final BallStopper ballStopper) {
    final JoystickAxis shootByVelocityAxis =
            buttonJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);
    shootByVelocityAxis.whileActiveContinuous(new ShootWithBallStopperTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> MAX_VELOCITY, () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED));

    buttonJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    shootByVelocityAxis.whileActiveContinuous(new ShootWithLoaderConveyorTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> MAX_VELOCITY, () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED));
  }
}
