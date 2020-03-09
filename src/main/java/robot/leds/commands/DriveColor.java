package robot.leds.commands;

import static robot.leds.RGBLedsConstants.YELLOW;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.leds.RGBLeds;

public class DriveColor extends InstantCommand {

  private final RGBLeds rgbLeds;

  public DriveColor(final RGBLeds rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void initialize() {
    rgbLeds.changeColor(YELLOW);
  }
}