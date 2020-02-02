package robot.crossSubsystem;

import edu.wpi.first.wpilibj.XboxController;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.LoaderConveyor.LoaderConveyor;
import robot.LoaderConveyor.LoaderConveyorConstants;
import robot.LoaderConveyor.commands.MoveLoaderByVelocity;
import robot.shooter.Shooter;
import robot.shooter.ShooterConstants;
import robot.shooter.commands.ShootByPercentOutput;
import robot.shooter.commands.ShootByVelocity;

public class CrossSubsystemOi {

  public CrossSubsystemOi(final UniqueAxisCache driveJoystickAxisCache,
                          final Shooter shooter, final LoaderConveyor loaderConveyor) {
    final JoystickAxis shootByVelocityAxis =
        driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);
    shootByVelocityAxis.whileActiveContinuous(new ShootByVelocity(shooter,
        () -> 20000)
        .alongWith(new MoveLoaderByVelocity(loaderConveyor, () -> shootByVelocityAxis.getRawAxis() * LoaderConveyorConstants.MAX_VELOCITY)));
  }

}
