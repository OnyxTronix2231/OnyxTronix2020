package robot.leds.commands;

import static robot.leds.RGBLedsConstants.PURPLE;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.leds.RGBLeds;

public class BallCollectorColor extends InstantCommand {

  private final RGBLeds rgbLeds;

  public BallCollectorColor(final RGBLeds rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void initialize() {
    rgbLeds.changeColor(PURPLE);
  }
}
