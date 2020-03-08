package robot.leds.commands;

import static robot.leds.RGBLedsConstants.CHAR_AT;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.leds.RGBLeds;
import robot.roulette.Roulette;

public class RouletteCurrentColor extends CommandBase {

  private final RGBLeds rgbLeds;
  private final Roulette roulette;

  public RouletteCurrentColor(final RGBLeds rgbLeds, final Roulette roulette) {
    this.rgbLeds = rgbLeds;
    this.roulette = roulette;
    addRequirements(rgbLeds);
  }

  @Override
  public void execute() {
    rgbLeds.changeColor(roulette.getCurrentColor().name().charAt(CHAR_AT));
  }
}