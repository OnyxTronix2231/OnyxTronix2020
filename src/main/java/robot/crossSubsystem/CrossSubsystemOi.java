package robot.crossSubsystem;

import static robot.crossSubsystem.CrossSubsystemConstants.MAX_VELOCITY;
import static robot.crossSubsystem.CrossSubsystemConstants.SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueButtonCache;
import robot.LoaderConveyor.LoaderConveyor;
import robot.LoaderConveyor.commands.MoveLoaderByVelocity;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByVelocity;

public class CrossSubsystemOi {

  public CrossSubsystemOi(final UniqueButtonCache driveJoystickButtonCache,
                          final Shooter shooter, final LoaderConveyor loaderConveyor) {
    final Trigger shootByVelocityButton =
        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);
    shootByVelocityButton.whileActiveContinuous(new ShootByVelocity(shooter,
        () -> SPEED * MAX_VELOCITY)
        .alongWith(new MoveLoaderByVelocity(loaderConveyor, () -> SPEED * MAX_VELOCITY)));
  }
}
