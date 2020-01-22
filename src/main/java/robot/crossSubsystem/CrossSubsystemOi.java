package robot.crossSubsystem;

import static robot.crossSubsystem.CrossSubsystemConstants.MAX_VELOCITY;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.BallStopper.BallStopper;
import robot.LoaderConveyor.LoaderConveyor;
import robot.StorageConveyor.StorageConveyor;
import robot.shooter.Shooter;

import static robot.crossSubsystem.CrossSubsystemConstants.*;

public class CrossSubsystemOi {

  public CrossSubsystemOi(final UniqueButtonCache driveJoystickButtonCache,
                          final Shooter shooter, final LoaderConveyor loaderConveyor,
                          final StorageConveyor storageConveyor,final BallStopper ballStopper) {
    final JoystickButton shootWithBallStopperTrigger =
            driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kY.value);
    final JoystickButton shootWithLoaderConveyorTrigger =
    driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);

    shootWithBallStopperTrigger.and(shootWithLoaderConveyorTrigger.negate()).whileActiveContinuous
        (new ShootWithBallStopperTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> MAX_VELOCITY, () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED));
    shootWithLoaderConveyorTrigger.and(shootWithBallStopperTrigger).whileActiveContinuous(
        new ShootWithLoaderConveyorTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> MAX_VELOCITY, () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED));
  }
}
