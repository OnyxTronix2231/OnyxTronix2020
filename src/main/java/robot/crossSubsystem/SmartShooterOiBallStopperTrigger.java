package robot.crossSubsystem;

import static robot.RobotConstants.ALIGNING_TIME_OUT;
import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_DELAY;
import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_CONVEYOR_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.CLOSE_RANGE_VELOCITY;
import static robot.crossSubsystem.CrossSubsystemConstants.STORAGE_SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballStopper.BallStopper;
import robot.crossSubsystem.commands.MoveConveyorsByBallStopperTrigger;
import robot.crossSubsystem.commands.MoveConveyorsByLoaderAsTrigger;
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
import robot.vision.Vision;

public class SmartShooterOiBallStopperTrigger {

  public SmartShooterOiBallStopperTrigger(final UniqueButtonCache driveJoystickButtonCache,
                                          final UniqueAxisCache driveJoystickAxisCache,
                                          final Shooter shooter, final LoaderConveyor loaderConveyor,
                                          final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                          final Vision vision) {
    final JoystickAxis shootWithBallStopperByDistance =
        driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);

    shootWithBallStopperByDistance.whileActiveContinuous(new SpinShooterAndLoaderByDistance(shooter, loaderConveyor,
        () -> vision.getOuterTarget().getDistance()));

    shootWithBallStopperByDistance.whileActiveContinuous(new MoveConveyorsByBallStopperTrigger(shooter, loaderConveyor,
        storageConveyor, ballStopper, () -> STORAGE_SPEED,
        () -> BALL_STOPPER_SPEED))
        .whenInactive(new StopShooter(shooter).alongWith(new StopLoaderConveyor(loaderConveyor)));

    final JoystickButton shootWithoutVision = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperRight.value);

    shootWithoutVision.whileActiveContinuous(new OpenShooterPiston(shooter)
        .andThen(((new ShootByVelocity(shooter, () -> CLOSE_RANGE_VELOCITY)))));

    shootWithoutVision.whileActiveContinuous(new MoveConveyorsByLoaderAsTrigger(shooter, loaderConveyor,
        storageConveyor, ballStopper, () -> LOADER_CONVEYOR_SPEED,
        () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED))
        .whenInactive(new CloseShooterPiston(shooter));

    final JoystickButton spinShooterWhileAligning = driveJoystickButtonCache
        .createJoystickTrigger(XboxController.Button.kBumperLeft.value, false);
    spinShooterWhileAligning.whenPressed(new ShootByDistance(shooter, () ->
        vision.getOuterTarget().getDistance()).withTimeout(ALIGNING_TIME_OUT)); //TODO: Add the timeout from YawControl
  }
}

