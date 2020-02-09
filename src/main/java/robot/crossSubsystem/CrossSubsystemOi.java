package robot.crossSubsystem;

import static robot.crossSubsystem.CrossSubsystemConstants.SHOOTER_VELOCITY;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.UniqueButtonCache;
import robot.BallStopper.BallStopper;
import robot.LoaderConveyor.LoaderConveyor;
import robot.LoaderConveyor.commands.StopLoaderConveyor;
import robot.StorageConveyor.StorageConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.StopShooter;

import static robot.crossSubsystem.CrossSubsystemConstants.*;

public class CrossSubsystemOi {

  public CrossSubsystemOi(final UniqueButtonCache driveJoystickButtonCache,
                          final Shooter shooter, final LoaderConveyor loaderConveyor,
                          final StorageConveyor storageConveyor, final BallStopper ballStopper) {
    final JoystickButton loadingShooter =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kY.value);
    loadingShooter.whenPressed (new SpinShooterAndLoader(shooter, loaderConveyor, () -> SHOOTER_VELOCITY)
        .withTimeout(SPIN_SHOOTER_AND_LOADER_TIME_OUT));

    final JoystickButton shootWithLoaderConveyorTrigger =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    final JoystickButton shootWithBallStopperTrigger =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);

    shootWithBallStopperTrigger.and(shootWithLoaderConveyorTrigger.negate()).
        whileActiveContinuous
            (new ShootWithBallStopperTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> SHOOTER_VELOCITY, () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED))
            .whenInactive(new StopShooter(shooter).alongWith(new StopLoaderConveyor(loaderConveyor)));
    shootWithLoaderConveyorTrigger.and(shootWithBallStopperTrigger).whileActiveContinuous(
        new ShootWithLoaderConveyorTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> SHOOTER_VELOCITY, () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED));
  }
}
