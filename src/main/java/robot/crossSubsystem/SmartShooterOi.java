package robot.crossSubsystem;

import static robot.RobotConstants.ALIGNING_TIME_OUT;
import static robot.crossSubsystem.CrossSubsystemConstants.OVERRIDE_SHOT_TIMEOUT;
import static robot.crossSubsystem.CrossSubsystemConstants.SHOOT_WITHOUT_VISION_SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCounter.BallCounter;
import robot.ballCounter.commands.WaitTillVelocityErrorThenCount;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperConstants;
import robot.crossSubsystem.commands.MoveAllConveyors;
import robot.crossSubsystem.commands.MoveConveyorsByLoaderAsTrigger;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorConstants;
import robot.shooter.Shooter;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;
import robot.shooter.commands.ShootByDistance;
import robot.shooter.commands.ShootByVelocity;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;
import robot.turret.commands.AlignByVision;
import robot.turret.commands.MoveTurretToAngleAndKeep;
import robot.vision.Vision;
import robot.yawControl.YawControl;

public class SmartShooterOi {

  public SmartShooterOi(final UniqueButtonCache driveJoystickButtonCache,
                        final UniqueAxisCache driveJoystickAxisCache,
                        final UniqueButtonCache buttonsJoystickButtonCache,
                        final Shooter shooter, final LoaderConveyor loaderConveyor,
                        final StorageConveyor storageConveyor, final BallStopper ballStopper,
                        final Vision vision, final YawControl yawControl, final BallCounter ballCounter) {
    final JoystickAxis shootWithLoaderTriggerByDistance =
        driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);

    final Trigger overrideTrigger = driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);

    shootWithLoaderTriggerByDistance.whileActiveContinuous(new ShootByDistance(shooter,
        () -> vision.getOuterTarget().getDistance()).alongWith(new AlignByVision(yawControl,
        vision::getDependableTarget)));

    shootWithLoaderTriggerByDistance.and(overrideTrigger.negate()).whileActiveContinuous(
        new MoveConveyorsByLoaderAsTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, yawControl, true));

    shootWithLoaderTriggerByDistance.whileActiveContinuous(new WaitTillVelocityErrorThenCount(shooter, ballCounter));

    overrideTrigger.and(shootWithLoaderTriggerByDistance).whenActive(
        new MoveAllConveyors(loaderConveyor, storageConveyor, ballStopper,
            () -> LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX,
        () -> StorageConveyorConstants.PERCENTAGE_OUTPUT, () -> BallStopperConstants.PERCENTAGE_OUTPUT)
        .withTimeout(OVERRIDE_SHOT_TIMEOUT));


    final JoystickButton shootWithoutVision = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperRight.value);

    shootWithoutVision.whileActiveContinuous(new CloseShooterPiston(shooter)
    .andThen(new ShootByVelocity(shooter,() -> SHOOT_WITHOUT_VISION_SPEED))).whenInactive(new OpenShooterPiston(shooter));

    shootWithoutVision.whileActiveOnce(new MoveTurretToAngleAndKeep(yawControl, () -> 0));

  shootWithoutVision.and(overrideTrigger.negate()).whileActiveContinuous(new MoveConveyorsByLoaderAsTrigger(shooter, loaderConveyor,
      storageConveyor, ballStopper, yawControl, false)).whenInactive(new OpenShooterPiston(shooter));

  shootWithoutVision.and(overrideTrigger).whileActiveOnce(
      new MoveAllConveyors(loaderConveyor, storageConveyor, ballStopper, () -> LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX,
      () -> StorageConveyorConstants.PERCENTAGE_OUTPUT, () -> BallStopperConstants.PERCENTAGE_OUTPUT)
      .withTimeout(OVERRIDE_SHOT_TIMEOUT));

    final JoystickButton spinShooterWhileAligningDrive = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperLeft.value, false);
    final JoystickButton spinShooterWhileAligningButton = buttonsJoystickButtonCache.createJoystickTrigger(
        XboxController.Button.kA.value, false
    );
      spinShooterWhileAligningDrive.or(spinShooterWhileAligningButton).toggleWhenActive(new ShootByDistance(shooter, () ->
      vision.getOuterTarget().getDistance()).withTimeout(ALIGNING_TIME_OUT));
  }
}
