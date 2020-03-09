package robot.roulette.commands;

import static robot.roulette.RouletteConstants.MINUS_ONE;

import robot.roulette.Roulette;
import robot.roulette.RouletteColor;
import robot.roulette.RouletteConstants;

import java.util.function.DoubleSupplier;

public class SpinRouletteByColorCount extends SpinRouletteToColor {

  private RouletteColor currentColor;
  private RouletteColor previousColor;

  public SpinRouletteByColorCount(final Roulette roulette, final DoubleSupplier colorsRequiredSupplier) {
    super(roulette, colorsRequiredSupplier);
  }

  @Override
  public void initialize() {
    previousColor = roulette.getCurrentColor();
    super.initialize();
  }

  @Override
  public void execute() {
    currentColor = roulette.getCurrentColor();
    if (currentColor.compareTo(previousColor) == 0) {
      previousColor = currentColor;
      colorsRequired = Math.copySign(Math.abs(colorsRequired) + MINUS_ONE, colorsRequired);
    }
    super.execute();
  }
}
