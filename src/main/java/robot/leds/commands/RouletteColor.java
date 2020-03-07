package robot.leds.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.leds.RGBLEDs;

public class RouletteColor extends CommandBase {

  private final RGBLEDs rgbLeds;

  public RouletteColor(RGBLEDs rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void execute() {
    if (!DriverStation.getInstance().getGameSpecificMessage().isEmpty())
      rgbLeds.changeColor(DriverStation.getInstance().getGameSpecificMessage().charAt(0));
  }
}
