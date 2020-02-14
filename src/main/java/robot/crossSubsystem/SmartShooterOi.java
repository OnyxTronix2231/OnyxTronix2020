package robot.crossSubsystem;

import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.SPIN_SHOOTER_AND_LOADER_TIME_OUT;
import static robot.crossSubsystem.CrossSubsystemConstants.STORAGE_SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.UniqueButtonCache;
import robot.ballStopper.BallStopper;
import robot.crossSubsystem.commands.ShootWithBallStopperByDistance;
import robot.crossSubsystem.commands.ShootWithLoaderConveyorByDistance;
import robot.crossSubsystem.commands.SpinShooterAndLoaderByDistance;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.StopLoaderConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByLimelight;
import robot.shooter.commands.StopShooter;
import robot.storageConveyor.StorageConveyor;
import robot.vision.VisionCalculations;
import robot.vision.limelight.Limelight;
import robot.vision.limelight.target.LimelightTarget;

public class SmartShooterOi {

  public SmartShooterOi(final UniqueButtonCache driveJoystickButtonCache,
                        final Shooter shooter, final LoaderConveyor loaderConveyor,
                        final StorageConveyor storageConveyor, final BallStopper ballStopper , final LimelightTarget target) {
    final JoystickButton loadingShooterByDistance =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kY.value);
    loadingShooterByDistance.whenPressed(new SpinShooterAndLoaderByDistance(shooter, loaderConveyor,
        () -> VisionCalculations.calculateDistance(target))
        .withTimeout(SPIN_SHOOTER_AND_LOADER_TIME_OUT));

    final JoystickButton shootWithLoaderConveyorByDistance =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    final JoystickButton shootWithBallStopperByDistance =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);

    shootWithBallStopperByDistance.and(shootWithLoaderConveyorByDistance.negate()).
        whileActiveContinuous
            (new ShootWithBallStopperByDistance(shooter, loaderConveyor,
                storageConveyor, ballStopper, () -> VisionCalculations.calculateDistance(target), () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED))
        .whenInactive(new StopShooter(shooter).alongWith(new StopLoaderConveyor(loaderConveyor)));
    shootWithLoaderConveyorByDistance.and(shootWithBallStopperByDistance).whileActiveContinuous(
        new ShootWithLoaderConveyorByDistance(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> VisionCalculations.calculateDistance(target),
            () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED));
  }
}
