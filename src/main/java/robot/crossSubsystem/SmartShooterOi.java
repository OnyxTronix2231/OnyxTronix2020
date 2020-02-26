package robot.crossSubsystem;

import static robot.RobotConstants.ALIGNING_TIME_OUT;
import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_CONVEYOR_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.SHOOTER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.STORAGE_SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperConstants;
import robot.crossSubsystem.commands.MoveAllConveyors;
import robot.crossSubsystem.commands.MoveConveyorsByLoaderAsTrigger;
import robot.crossSubsystem.commands.WaitUntilBallInLoader;
import robot.crossSubsystem.commands.WaitUntilBallIsNotInLoader;
import robot.drivetrain.DriveTrain;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorConstants;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.shooter.Shooter;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;
import robot.shooter.commands.ShootByDistance;
import robot.shooter.commands.ShootByVelocity;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;
import robot.vision.Vision;
import robot.yawControl.YawControl;
import robot.yawControl.commands.AlignByVisionOrOrientationAndVision;

public class SmartShooterOi {

  public SmartShooterOi(final UniqueButtonCache driveJoystickButtonCache,
                        final UniqueAxisCache driveJoystickAxisCache,
                        final UniqueButtonCache buttonsJoystickButtonCache,
                        final Shooter shooter, final LoaderConveyor loaderConveyor,
                        final StorageConveyor storageConveyor, final BallStopper ballStopper,
                        final Vision vision, final YawControl yawControl, final DriveTrain driveTrain) {

    final JoystickAxis shootWithLoaderTriggerByDistance =
        driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);

    final Trigger overrideTrigger = driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);

    shootWithLoaderTriggerByDistance.whileActiveContinuous(new ShootByDistance(shooter,
        () -> vision.getOuterTarget().getDistance()).alongWith(new AlignByVisionOrOrientationAndVision(yawControl, driveTrain,
        vision::getDependableTarget)));

    overrideTrigger.and(shootWithLoaderTriggerByDistance).whileActiveOnce(new ParallelDeadlineGroup(new WaitUntilBallIsNotInLoader(loaderConveyor),
        new MoveAllConveyors(loaderConveyor, ballStopper, storageConveyor, () -> LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX,
            () -> StorageConveyorConstants.PERCENTAGE_OUTPUT, () -> BallStopperConstants.PERCENTAGE_OUTPUT)));

    shootWithLoaderTriggerByDistance.and(overrideTrigger.negate()).whileActiveContinuous(
        new MoveConveyorsByLoaderAsTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> LOADER_CONVEYOR_SPEED,
            () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED));

    final JoystickButton shootWithoutVision = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperRight.value);

    shootWithoutVision.whileActiveContinuous(new CloseShooterPiston(shooter)
    .andThen(new ShootByVelocity(shooter,() -> SHOOTER_SPEED)));

  shootWithoutVision.and(overrideTrigger.negate()).whileActiveContinuous(new MoveConveyorsByLoaderAsTrigger(shooter, loaderConveyor,
      storageConveyor, ballStopper, () -> LOADER_CONVEYOR_SPEED,
     () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED)).whenInactive(new OpenShooterPiston(shooter));

  shootWithoutVision.and(overrideTrigger).whileActiveOnce(new ParallelDeadlineGroup(new WaitUntilBallIsNotInLoader(loaderConveyor),
      new MoveAllConveyors(loaderConveyor, ballStopper, storageConveyor, () -> LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX,
          () -> StorageConveyorConstants.PERCENTAGE_OUTPUT, () -> BallStopperConstants.PERCENTAGE_OUTPUT)));

    final JoystickButton spinShooterWhileAligningDrive = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperLeft.value, false);
    final JoystickButton spinShooterWhileAligningButton = buttonsJoystickButtonCache.createJoystickTrigger(
        XboxController.Button.kA.value, false
    );
      spinShooterWhileAligningDrive.or(spinShooterWhileAligningButton).toggleWhenActive(new ShootByDistance(shooter, () ->
      vision.getOuterTarget().getDistance()).withTimeout(ALIGNING_TIME_OUT));
  }
}
