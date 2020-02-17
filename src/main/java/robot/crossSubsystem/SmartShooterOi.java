package robot.crossSubsystem;

import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_CONVEYOR_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.SPIN_SHOOTER_AND_LOADER_TIME_OUT;
import static robot.crossSubsystem.CrossSubsystemConstants.STORAGE_SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.UniqueButtonCache;
import robot.ballStopper.BallStopper;
import robot.crossSubsystem.commands.MoveConveyorsByBallStopperTrigger;
import robot.crossSubsystem.commands.MoveConveyorsByLoaderConveyorTrigger;
import robot.crossSubsystem.commands.SpinShooterAndLoaderByDistance;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.StopLoaderConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByDistance;
import robot.shooter.commands.StopShooter;
import robot.storageConveyor.StorageConveyor;

public class SmartShooterOi {

  public SmartShooterOi(final UniqueButtonCache driveJoystickButtonCache,
                        final Shooter shooter, final LoaderConveyor loaderConveyor,
                        final StorageConveyor storageConveyor, final BallStopper ballStopper, final LimelightTarget target) {
    final JoystickButton loadingShooterByDistance =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kY.value);
    loadingShooterByDistance.whenPressed(new SpinShooterAndLoaderByDistance(shooter, loaderConveyor,
        () -> VisionCalculations.calculateDistance(target))
        .withTimeout(SPIN_SHOOTER_AND_LOADER_TIME_OUT));

    final JoystickButton moveConveyorsByLoaderConveyorTriggerButton =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    final JoystickButton shootWithBallStopperByDistance =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);

    shootWithBallStopperByDistance.and(moveConveyorsByLoaderConveyorTriggerButton.negate()).
        whileActiveContinuous(new SpinShooterAndLoaderByDistance(shooter,loaderConveyor,
            () -> VisionCalculations.calculateDistance(target)));

    shootWithBallStopperByDistance.and(moveConveyorsByLoaderConveyorTriggerButton.negate()).
        whileActiveContinuous
            (new MoveConveyorsByBallStopperTrigger(shooter, loaderConveyor,
                storageConveyor, ballStopper, () -> STORAGE_SPEED,
                 () -> BALL_STOPPER_SPEED))
        .whenInactive(new StopShooter(shooter).alongWith(new StopLoaderConveyor(loaderConveyor)));


    moveConveyorsByLoaderConveyorTriggerButton.and(shootWithBallStopperByDistance).
        whileActiveContinuous(new ShootByDistance(shooter,
            () -> VisionCalculations.calculateDistance(target)));

    moveConveyorsByLoaderConveyorTriggerButton.and(shootWithBallStopperByDistance).whileActiveContinuous(
        new MoveConveyorsByLoaderConveyorTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> LOADER_CONVEYOR_SPEED,
            () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED)).whenInactive(new StopShooter(shooter));


  }
}
