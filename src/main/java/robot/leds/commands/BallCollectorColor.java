package robot.leds.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ballCollector.BallCollector;
import robot.leds.RGBLEDs;
import robot.leds.RGBLEDsConstants;

public class BallCollectorColor extends CommandBase {

  private final BallCollector ballCollector;
  private final RGBLEDs rgbLeds;

  public BallCollectorColor(final BallCollector ballCollector, final RGBLEDs rgbLeds) {
    this.ballCollector = ballCollector;
    this.rgbLeds = rgbLeds;
  }

  @Override
  public void execute() {
    rgbLeds.changeColor(RGBLEDsConstants.PURPLE);
  }
}
