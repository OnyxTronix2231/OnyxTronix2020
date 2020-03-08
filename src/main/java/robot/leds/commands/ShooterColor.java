package robot.leds.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import robot.leds.RGBLeds;
import robot.shooter.Shooter;

public class ShooterColor extends ConditionalCommand {

  public ShooterColor(final Shooter shooter, final RGBLeds rgbLeds) {
    super(new ShooterColorOnTarget(rgbLeds), new ShooterColorNotOnTarget(rgbLeds), shooter::isOnTarget);
  }
}
