package robot.crossSubsystem;

import static robot.RobotConstants.ALIGNING_TIME_OUT;
import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_CONVEYOR_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.SHOOTER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.STORAGE_SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.BallCollector;
import robot.ballStopper.BallStopper;
import robot.crossSubsystem.commands.MoveConveyorsByLoaderAsTrigger;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;
import robot.shooter.commands.ShootByDistance;
import robot.shooter.commands.ShootByVelocity;
import robot.storageConveyor.StorageConveyor;
import robot.vision.Vision;

public class SmartShooterOi {

  public SmartShooterOi(final UniqueButtonCache driveJoystickButtonCache,
                        final UniqueAxisCache driveJoystickAxisCache,
                        final Shooter shooter, final LoaderConveyor loaderConveyor,
                        final StorageConveyor storageConveyor, final BallStopper ballStopper,
                        final BallCollector ballCollector, final Vision vision) {
    final JoystickAxis shootWithLoaderTriggerByDistance =
        driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);

//    shootWithLoaderTriggerByDistance.whileActiveContinuous(new ShootByDistance(shooter,
//        () -> vision.getOuterTarget().getDistance()));
//    shootWithLoaderTriggerByDistance.whileActiveContinuous(new ShootAndCountByDistance(shooter, ballCollector,
//            () -> vision.getOuterTarget().getDistance()));

    shootWithLoaderTriggerByDistance.whileActiveContinuous(
        new MoveConveyorsByLoaderAsTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> LOADER_CONVEYOR_SPEED,
            () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED));

    final JoystickButton shootWithoutVision = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperRight.value);

    shootWithoutVision.whileActiveContinuous(new CloseShooterPiston(shooter)
    .alongWith(new ShootByVelocity(shooter,() -> SHOOTER_SPEED)));

    shootWithoutVision.whileActiveContinuous(new MoveConveyorsByLoaderAsTrigger(shooter, loaderConveyor,
        storageConveyor, ballStopper, () -> LOADER_CONVEYOR_SPEED,
        () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED)).whenInactive(new OpenShooterPiston(shooter));

    final JoystickButton spinShooterWhileAligning = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperLeft.value, false);
      spinShooterWhileAligning.toggleWhenPressed(new ShootByDistance(shooter, () ->
      vision.getOuterTarget().getDistance()).withTimeout(ALIGNING_TIME_OUT));
  }
}
