package robot.roulette;

import static robot.roulette.RouletteConstants.COLORS_IN_ROTATIONS;
import static robot.roulette.RouletteConstants.DISTANCE_FROM_FIELD_SENSOR;
import static robot.roulette.RouletteConstants.ENCODER_UNITS_PER_ROTATION;
import static robot.roulette.RouletteConstants.RouletteComponentsA.ROULETTE_ROTATION_TO_WHEEL_ROTATION;
import static robot.roulette.RouletteConstants.TOLERANCE;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Roulette extends SubsystemBase {

  private final RouletteComponents components;
  private final List<RouletteColor> rouletteColors;

  public Roulette(final RouletteComponents components) {
    this.components = components;
    this.rouletteColors = Arrays.asList(RouletteColor.values());
  }

  public void moveRouletteBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void openPistons() {
    components.getDoubleSolenoid().set(DoubleSolenoid.Value.kForward);
  }

  public void closePistons() {
    components.getDoubleSolenoid().close();
  }

  public RouletteColor getRequiredColorFromMatchColor() {
    if (DriverStation.getInstance().getGameSpecificMessage().isEmpty()) {
      return null;
    }
    switch (DriverStation.getInstance().getGameSpecificMessage().charAt(0)) {
      case 'B':
        return RouletteColor.Blue;
      case 'G':
        return RouletteColor.Green;
      case 'R':
        return RouletteColor.Red;
      case 'Y':
        return RouletteColor.Yellow;
      default:
        return null;
    }
  }

  public RouletteColor getCurrentColor() {
    final Color detectedColor = components.getColorSensor().getColor();
    final RouletteColor[] rouletteColors = RouletteColor.values();
    final Optional<RouletteColor> roulette = Arrays.stream(rouletteColors).max(
        Comparator.comparing(color -> color.getRgbValue().howCloseTo(detectedColor)));
    return roulette.orElse(null);
  }

  public void stopMotor() {
    moveRouletteBySpeed(0);
  }

  public void spinByColorsCount(final double requiredColorCount) {
    getRouletteRotationToEncoderUnits(getRouletteRotationByColorCount(requiredColorCount));
  }

  public double getRouletteRotationToEncoderUnits(final double rouletteRotations) {
    return rouletteRotations * ENCODER_UNITS_PER_ROTATION * ROULETTE_ROTATION_TO_WHEEL_ROTATION;
  }

  public double getRouletteRotationByColorCount(final double colorCount) {
    return colorCount / COLORS_IN_ROTATIONS;
  }

  /**
   * @return When return value is positive, the roulette needs to be rotated clockwise
   */

  public int getColorCountRequiredToColor(final RouletteColor requiredColor) {
   final int indexOfRequiredColor = rouletteColors.indexOf(requiredColor);
   final int indexOfCurrentColor = rouletteColors.indexOf(getCurrentColor());
   int colorDistance = Math.abs(indexOfRequiredColor - indexOfCurrentColor);
    return colorDistance - DISTANCE_FROM_FIELD_SENSOR;
  }

  public void resetEncoder() {
    components.getMasterMotor().setSelectedSensorPosition(0);
  }

  public boolean isOnTarget() {
    return Math.abs(components.getMasterMotor().getClosedLoopError()) <= TOLERANCE;
  }

  public boolean isOnTargetColor(final double colorsRequired) {
    return colorsRequired == 0;
  }
}
