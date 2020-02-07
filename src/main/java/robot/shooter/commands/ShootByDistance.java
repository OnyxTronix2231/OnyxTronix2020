package robot.shooter.commands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

import static robot.shooter.ShooterConstants.*;

public class ShootByDistance extends ShootByVelocity {

  public ShootByDistance(final Shooter shooter, final DoubleSupplier distanceSupplier) {
    super(shooter, () -> {
      double distance = distanceSupplier.getAsDouble();
      if (distance > MIN_THIRD_RANGE_CM && distance < MAX_THIRD_RANGE_CM) {
        return SPEED_THIRD;
      } else if (distance > MAX_FIRST_RANGE_CM && distance < MIN_THIRD_RANGE_CM) {
        return SPEED_MIDDLE;
      } else if (distance > MIN_FIRST_RANGE_CM){
        return SPEED_FIRST;
      } else {
        return 0;
      }
    });
  }
}
