package robot.roulette.commands;

import static robot.roulette.RouletteConstants.AVERAGE_COLOR_COUNT;

import robot.roulette.Roulette;

import java.util.function.DoubleSupplier;

public class SpinRouletteToAverageRotations extends SpinRouletteToColorPeriodically {
  public SpinRouletteToAverageRotations(final Roulette roulette) {
    super(roulette, () -> AVERAGE_COLOR_COUNT);
  }
}
