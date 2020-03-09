package robot.leds.commands;

import static robot.leds.RGBLedsConstants.BLUE;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.leds.RGBLeds;

public class ShooterColorNotOnTarget extends InstantCommand {

  private final RGBLeds rgbLeds;

  public ShooterColorNotOnTarget(final RGBLeds rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void initialize() {
    rgbLeds.changeColor(BLUE);
  }
}
