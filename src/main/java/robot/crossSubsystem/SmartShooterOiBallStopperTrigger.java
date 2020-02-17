package robot.crossSubsystem;

import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_CONVEYOR_SPEED;
import static robot.crossSubsystem.CrossSubsystemConstants.STORAGE_SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.BallCollector;
import robot.ballStopper.BallStopper;
import robot.crossSubsystem.commands.MoveConveyorsByLoaderConveyorTrigger;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByDistance;
import robot.storageConveyor.StorageConveyor;
import robot.vision.target.VisionTargetFactory;
import robot.vision.target.VisionTargetType;

public class SmartShooterOiBallStopperTrigger {

  public SmartShooterOiBallStopperTrigger(final UniqueButtonCache driveJoystickButtonCache,
                                          final Shooter shooter , final LoaderConveyor loaderConveyor,
                                          final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                          final VisionTargetFactory factory){

    final JoystickButton shootWithBallStopperByDistance =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);

    (shootWithBallStopperByDistance).
        whileActiveContinuous(new ShootByDistance(shooter,
            () -> factory.makeTarget(VisionTargetType.OUTER_TARGET).getDistance()));

    (shootWithBallStopperByDistance).whileActiveContinuous(
        new MoveConveyorsByLoaderConveyorTrigger(shooter, loaderConveyor,
            storageConveyor, ballStopper, () -> LOADER_CONVEYOR_SPEED,
            () -> STORAGE_SPEED, () -> BALL_STOPPER_SPEED));
  }
}
