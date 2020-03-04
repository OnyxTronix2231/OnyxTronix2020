package robot.leds.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.leds.RGBLEDs;
import robot.leds.RGBLEDsConstants;
import robot.shooter.Shooter;

public class ShooterColor extends CommandBase {

  private final Shooter shooter;
  private final RGBLEDs rgbLeds;

  public ShooterColor(final Shooter shooter, final RGBLEDs rgbLeds) {
    this.shooter = shooter;
    this.rgbLeds = rgbLeds;
  }

  @Override
  public void execute() {
      if (shooter.isOnTarget())
        rgbLeds.changeColor(RGBLEDsConstants.GREEN);
      rgbLeds.changeColor(RGBLEDsConstants.BLUE);
  }
}
