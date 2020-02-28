package robot.autonomous.commands;

import static robot.autonomous.AutonomousConstants.AUTONOMOUS_DISTANCE;
import static robot.autonomous.AutonomousConstants.DRIVE_AUTONOMOUS_TIMEOUT;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballStopper.BallStopper;
import robot.crossSubsystem.commands.MoveConveyorsByLoaderAsTrigger;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.DriveByDistance;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByDistance;
import robot.storageConveyor.StorageConveyor;
import robot.vision.Vision;
import robot.yawControl.YawControl;
import robot.yawControl.commands.AlignByOrientationAndThenVision;

public class DriveThenShootAutonomous extends SequentialCommandGroup {

  public DriveThenShootAutonomous(final YawControl yawControl, final DriveTrain driveTrain, final Shooter shooter,
                                  final LoaderConveyor loaderConveyor, final StorageConveyor storageConveyor,
                                  final BallStopper ballStopper, final Vision vision) {
    super(new DriveByDistance(driveTrain, () -> AUTONOMOUS_DISTANCE).withTimeout(DRIVE_AUTONOMOUS_TIMEOUT),
            parallel(
        new AlignByOrientationAndThenVision(yawControl, vision::getDependableTarget),
        new ShootByDistance(shooter, () -> vision.getOuterTarget().getDistance()),
        new MoveConveyorsByLoaderAsTrigger(shooter, loaderConveyor, storageConveyor, ballStopper, yawControl,
            true)));

  }
}
