package robot.roulette;

import static robot.roulette.RouletteConstants.BLUE_RGB_VALUE;
import static robot.roulette.RouletteConstants.GREEN_RGB_VALUE;
import static robot.roulette.RouletteConstants.RED_RGB_VALUE;
import static robot.roulette.RouletteConstants.YELLOW_RGB_VALUE;

public enum RouletteColor {
  Green(GREEN_RGB_VALUE),
  Blue(BLUE_RGB_VALUE),
  Yellow(YELLOW_RGB_VALUE),
  Red(RED_RGB_VALUE);

  public final RGBValue rgbValue;

  RouletteColor (final RGBValue rgbValue) {
    this.rgbValue = rgbValue;
  }

  public RGBValue getRgbValue() {
    return rgbValue;
  }
}
