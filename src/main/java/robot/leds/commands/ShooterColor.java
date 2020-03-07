package robot.leds.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.leds.RGBLeds;
import robot.leds.RGBLedsConstants;
import robot.shooter.Shooter;

public class ShooterColor extends CommandBase {

  private final Shooter shooter;
  private final RGBLeds rgbLeds;

  public ShooterColor(final Shooter shooter, final RGBLeds rgbLeds) {
    this.shooter = shooter;
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void execute() {
    if (shooter.isOnTarget())
      rgbLeds.changeColor(RGBLedsConstants.GREEN);
    rgbLeds.changeColor(RGBLedsConstants.BLUE);
  }
}
