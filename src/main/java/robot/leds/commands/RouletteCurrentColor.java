package robot.leds.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.leds.RGBLEDs;
import robot.roulette.Roulette;

public class RouletteCurrentColor extends CommandBase {

  private final RGBLEDs rgbLeds;
  private final Roulette roulette;

  public RouletteCurrentColor(RGBLEDs rgbLeds, Roulette roulette) {
    this.rgbLeds = rgbLeds;
    this.roulette = roulette;
    addRequirements(rgbLeds);
  }

  @Override
  public void execute() {
    rgbLeds.changeColor(roulette.getCurrentColor().name().charAt(0));
  }
}