package robot.crossSubsystem;

import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_CONVEYOR_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.SHOOTER_VELOCITY;
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
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;
import robot.shooter.commands.ShootByDistance;
import robot.shooter.commands.ShootByVelocity;
import robot.shooter.commands.StopShooter;
import robot.storageConveyor.StorageConveyor;
import robot.vision.target.VisionTargetFactory;
import robot.vision.target.VisionTargetType;

public class SmartShooterOi {

  public SmartShooterOi(final UniqueButtonCache driveJoystickButtonCache,
                        final Shooter shooter, final LoaderConveyor loaderConveyor,
                        final StorageConveyor storageConveyor, final BallStopper ballStopper,
                        final VisionTargetFactory factory) {
    final JoystickButton loadingShooterByDistance =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kY.value);
    loadingShooterByDistance.whenPressed(new SpinShooterAndLoaderByDistance(shooter, loaderConveyor,
        () -> factory.makeTarget(VisionTargetType.OUTER_TARGET).getDistance())
        .withTimeout(SPIN_SHOOTER_AND_LOADER_TIME_OUT));

    final JoystickButton moveConveyorsByLoaderConveyorTriggerButton =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStickLeft.value);
    final JoystickButton shootWithBallStopperByDistance =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);

    shootWithBallStopperByDistance.and(moveConveyorsByLoaderConveyorTriggerButton.negate()).
        whileActiveContinuous(new SpinShooterAndLoaderByDistance(shooter,loaderConveyor,
            () -> factory.makeTarget(VisionTargetType.OUTER_TARGET).getDistance()));

    shootWithBallStopperByDistance.and(moveConveyorsByLoaderConveyorTriggerButton.negate()).
        whileActiveContinuous
            (new MoveConveyorsByBallStopperTrigger(shooter, loaderConveyor,
                storageConveyor, ballStopper, () -> STORAGE_SPEED,
                 () -> BALL_STOPPER_SPEED))
        .whenInactive(new StopShooter(shooter).alongWith(new StopLoaderConveyor(loaderConveyor)));

    moveConveyorsByLoaderConveyorTriggerButton.and(shootWithBallStopperByDistance).
        whileActiveContinuous(new ShootByDistance(shooter,
            () -> factory.makeTarget(VisionTargetType.OUTER_TARGET).getDistance()));

    moveConveyorsByLoaderConveyorTriggerButton.and(shootWithBallStopperByDistance).whileActiveContinuous(
        new MoveConveyorsByLoaderConveyorTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> LOADER_CONVEYOR_SPEED,
            () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED));

    final JoystickButton ShootWithoutLimeLight = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperRight.value);

    ShootWithoutLimeLight.whileActiveContinuous(new OpenShooterPiston(shooter).
        andThen(new ShootByVelocity(shooter, () -> SHOOTER_VELOCITY)));

    ShootWithoutLimeLight.whileActiveContinuous(new MoveConveyorsByLoaderConveyorTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> LOADER_CONVEYOR_SPEED,
            () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED)).whenInactive(new StopShooter(shooter)
        .alongWith(new CloseShooterPiston(shooter)));

    final JoystickButton MoveShooterWheel = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperLeft.value,false);
    MoveShooterWheel.whenPressed(new ShootByDistance(shooter,   () ->
        factory.makeTarget(VisionTargetType.OUTER_TARGET).getDistance()));
  }
}
