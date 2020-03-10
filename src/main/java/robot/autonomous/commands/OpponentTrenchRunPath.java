package robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.autonomous.AutonomousConstants;
import robot.ballCollector.BallCollector;
import robot.ballStopper.BallStopper;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.MoveToPose;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.storageConveyor.StorageConveyor;
import robot.vision.Vision;

public class OpponentTrenchRunPath extends SequentialCommandGroup {

  public OpponentTrenchRunPath(final DriveTrain driveTrain, final Shooter shooter, final Vision vision,
                               final BallCollector ballCollector, final LoaderConveyor loaderConveyor,
                               final StorageConveyor storageConveyor, final BallStopper ballStopper){
    super(new MoveToPose(driveTrain, AutonomousConstants.Paths.));
  }
}
