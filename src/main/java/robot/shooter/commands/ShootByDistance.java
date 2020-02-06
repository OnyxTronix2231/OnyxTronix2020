package robot.shooter.commands;

import static robot.shooter.ShooterConstants.MAX_FIRST_RANGE_CM;
import static robot.shooter.ShooterConstants.MIN_THIRD_RANGE_CM;
import static robot.shooter.ShooterConstants.SPEED_FIRST;
import static robot.shooter.ShooterConstants.SPEED_MIDDLE;
import static robot.shooter.ShooterConstants.SPEED_THIRD;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByDistance extends ShootByVelocity {

  public ShootByDistance(final Shooter shooter, final DoubleSupplier distanceSupplier) {
    super(shooter, () -> {
      double distance = distanceSupplier.getAsDouble();
      if (distance > MIN_THIRD_RANGE_CM) {
        return SPEED_THIRD;
      } else if (distance > MAX_FIRST_RANGE_CM && distance < MIN_THIRD_RANGE_CM) {
        return SPEED_MIDDLE;
      } else {
        return SPEED_FIRST;
      }
    });
  }
}
