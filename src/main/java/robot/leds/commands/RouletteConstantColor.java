package robot.leds.commands;

import static robot.leds.RGBLedsConstants.CHAR_AT;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.leds.RGBLeds;

public class RouletteConstantColor extends InstantCommand {

  private final RGBLeds rgbLeds;

  public RouletteConstantColor(final RGBLeds rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void initialize() {
    if (!DriverStation.getInstance().getGameSpecificMessage().isEmpty())
      rgbLeds.changeColor(DriverStation.getInstance().getGameSpecificMessage().charAt(CHAR_AT));
  }
}
