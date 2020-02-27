package robot.autonomous.commands;

import static robot.autonomous.AutonomousConstants.AUTONOMOUS_DISTANCE;
import static robot.autonomous.AutonomousConstants.BALL_STOPPER_VELOCITY;
import static robot.autonomous.AutonomousConstants.LOADER_VELOCITY;
import static robot.autonomous.AutonomousConstants.STORAGE_VELOCITY;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballStopper.BallStopper;
import robot.crossSubsystem.commands.MoveConveyorsByLoaderAsTriggerWithVision;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.DriveByDistance;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByDistance;
import robot.storageConveyor.StorageConveyor;
import robot.vision.Vision;
import robot.yawControl.YawControl;
import robot.yawControl.commands.AlignByVisionOrOrientationAndVision;

public class AutonomousShooting extends SequentialCommandGroup {

  public AutonomousShooting(final YawControl yawControl, final DriveTrain driveTrain, final Shooter shooter,
                            final LoaderConveyor loaderConveyor, final StorageConveyor storageConveyor,
                            final BallStopper ballStopper, final Vision vision) {
    super(new DriveByDistance(driveTrain, () -> AUTONOMOUS_DISTANCE).withTimeout(5),
            parallel(
        new AlignByVisionOrOrientationAndVision(yawControl, driveTrain, vision::getDependableTarget),
        new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()),
        new MoveConveyorsByLoaderAsTriggerWithVision(shooter, loaderConveyor, storageConveyor, ballStopper, yawControl,
            () -> LOADER_VELOCITY, () -> STORAGE_VELOCITY, () -> BALL_STOPPER_VELOCITY)));

  }
}
