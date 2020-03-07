package robot.leds.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.leds.RGBLeds;
import robot.leds.RGBLedsConstants;

public class BallCollectorColor extends CommandBase {

  private final RGBLeds rgbLeds;

  public BallCollectorColor(final RGBLeds rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void execute() {
    rgbLeds.changeColor(RGBLedsConstants.PURPLE);
  }
}
