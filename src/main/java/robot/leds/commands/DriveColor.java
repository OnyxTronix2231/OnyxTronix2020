package robot.leds.commands;

import static robot.leds.RGBLedsConstants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.leds.RGBLeds;
import robot.leds.RGBLedsConstants;

public class DriveColor extends CommandBase {
  // Make instant command
  private final RGBLeds rgbLeds;

  public DriveColor(final RGBLeds rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void execute() {
    rgbLeds.changeColor(YELLOW);
  }
}
