package robot.crossSubsystem;

import static robot.crossSubsystem.CrossSubsystemConstants.MAX_VELOCITY;

import edu.wpi.first.wpilibj.XboxController;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.LoaderConveyor.LoaderConveyor;
import robot.LoaderConveyor.commands.MoveLoaderByVelocity;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByVelocity;

public class CrossSubsystemOi {

  public CrossSubsystemOi(final UniqueAxisCache driveJoystickAxisCache,
                          final Shooter shooter, final LoaderConveyor loaderConveyor) {
    final JoystickAxis shootByVelocityAxis =
        driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);
    shootByVelocityAxis.whileActiveContinuous(new ShootByVelocity(shooter,
        () -> shootByVelocityAxis.getRawAxis() * MAX_VELOCITY)
        .alongWith(new MoveLoaderByVelocity(loaderConveyor, () -> shootByVelocityAxis.getRawAxis() * MAX_VELOCITY)));
  }
}
