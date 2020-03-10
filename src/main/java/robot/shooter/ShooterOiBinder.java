package robot.shooter;

import static robot.RobotConstants.ALIGNING_TIME_OUT;
import static robot.ballTrigger.BallTriggerConstants.SHOOT_WITHOUT_VISION_SPEED;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.shooter.commands.CloseShooterPiston;
import robot.shooter.commands.OpenShooterPiston;
import robot.shooter.commands.ShootByDistance;
import robot.shooter.commands.ShootByVelocity;

import java.util.function.DoubleSupplier;

public class ShooterOiBinder {
  public ShooterOiBinder(final Shooter shooter, final DoubleSupplier distanceSupplier,
                         final Trigger spinShooterWithTimeout, final Trigger spinShooter,
                         final Trigger shootAtCloseRange) {
    spinShooterWithTimeout.toggleWhenActive(new ShootByDistance(shooter, distanceSupplier).
        withTimeout(ALIGNING_TIME_OUT));

    spinShooter.whileActiveContinuous(new ShootByDistance(shooter, distanceSupplier));

    shootAtCloseRange.whileActiveContinuous(new CloseShooterPiston(shooter)
        .andThen(new ShootByVelocity(shooter, () -> SHOOT_WITHOUT_VISION_SPEED))).
        whenInactive(new OpenShooterPiston(shooter));
  }
}
