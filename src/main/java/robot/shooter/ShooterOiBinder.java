package robot.shooter;

import static robot.RobotConstants.ALIGNING_TIME_OUT;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;
import robot.shooter.commands.ShootByDistance;

import java.util.function.DoubleSupplier;

public class ShooterOiBinder {
  public ShooterOiBinder(final Shooter shooter, final DoubleSupplier distanceSupplier,
                         final Trigger spinShooterWithTimeout, final Trigger spinShooter,
                         final Trigger shootAtCloseRange) {
    spinShooterWithTimeout.toggleWhenActive(new ShootByDistance(shooter, distanceSupplier).
        withTimeout(ALIGNING_TIME_OUT));

    spinShooter.whileActiveContinuous(new ShootByDistance(shooter, distanceSupplier));

    shootAtCloseRange.whileActiveContinuous(new CloseShooterPiston(shooter)
        .andThen(new ShootByDistance(shooter, distanceSupplier))).
        whenInactive(new OpenShooterPiston(shooter));
  }
}
