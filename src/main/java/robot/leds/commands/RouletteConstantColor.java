package robot.leds.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.leds.RGBLeds;

public class RouletteConstantColor extends CommandBase {

  private final RGBLeds rgbLeds;

  public RouletteConstantColor(final RGBLeds rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void execute() {
    if (!DriverStation.getInstance().getGameSpecificMessage().isEmpty())
      rgbLeds.changeColor(DriverStation.getInstance().getGameSpecificMessage().charAt(0));
  }
}
