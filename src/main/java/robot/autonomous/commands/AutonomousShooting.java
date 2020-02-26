package robot.autonomous.commands;

import static robot.autonomous.AutonomousConstants.BALL_STOPPER_VELOCITY;
import static robot.autonomous.AutonomousConstants.LOADER_VELOCITY;
import static robot.autonomous.AutonomousConstants.STORAGE_VELOCITY;
import static robot.autonomous.AutonomousConstants.TIMER;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.ballStopper.BallStopper;
import robot.crossSubsystem.commands.MoveConveyorsByLoaderAsTrigger;
import robot.drivetrain.DriveTrain;
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
    super(deadline(new WaitCommand(TIMER), parallel(
        new AlignByVisionOrOrientationAndVision(yawControl, driveTrain, vision::getDependableTarget),
        new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()),
        new MoveConveyorsByLoaderAsTrigger(shooter, loaderConveyor, storageConveyor, ballStopper,
            () -> LOADER_VELOCITY, () -> STORAGE_VELOCITY, () -> BALL_STOPPER_VELOCITY))));

  }
}
