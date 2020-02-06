package robot.crossSubsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.LoaderConveyor.LoaderConveyor;
import robot.LoaderConveyor.LoaderConveyorConstants;
import robot.LoaderConveyor.commands.MoveLoaderByVelocity;
import robot.shooter.Shooter;
import robot.shooter.ShooterConstants;
import robot.shooter.commands.ShootByDistance;
import robot.shooter.commands.ShootByLimelight;
import robot.shooter.commands.ShootByPercentOutput;
import robot.shooter.commands.ShootByVelocity;
import robot.vision.VisionTarget;
import robot.vision.limelight.Limelight;

public class CrossSubsystemOi {

  public CrossSubsystemOi(final UniqueAxisCache driveJoystickAxisCache,
                          final UniqueButtonCache driveJoystickButtonCache, final Shooter shooter, final LoaderConveyor loaderConveyor) {
    final JoystickAxis shootByVelocityAxis =
        driveJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);
    shootByVelocityAxis.whileActiveContinuous(new ShootByVelocity(shooter,
        () -> 35000)
        .alongWith(new MoveLoaderByVelocity(loaderConveyor, () -> shootByVelocityAxis.getRawAxis() * LoaderConveyorConstants.MAX_VELOCITY)));

    final Trigger shootByDistanceTrigger = driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);
    shootByDistanceTrigger.whenActive(new ShootByLimelight(shooter).raceWith
        (new MoveLoaderByVelocity(loaderConveyor, () -> LoaderConveyorConstants.MAX_VELOCITY)));

  }

}
