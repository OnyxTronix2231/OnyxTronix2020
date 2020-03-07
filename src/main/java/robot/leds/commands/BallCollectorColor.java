package robot.leds.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.leds.RGBLEDs;
import robot.leds.RGBLEDsConstants;

public class BallCollectorColor extends CommandBase {

  private final RGBLEDs rgbLeds;

  public BallCollectorColor(final RGBLEDs rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void execute() {
    rgbLeds.changeColor(RGBLEDsConstants.PURPLE);
  }
}
