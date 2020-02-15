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
  double rouletteRotation = 0;

  public Roulette(final RouletteComponents components) {
    this.components = components;
    this.rouletteColors = Arrays.asList(RouletteColor.values());
  }

  public void moveRouletteBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void openPistons() {
    components.getDoubleRightSolenoid().set(DoubleSolenoid.Value.kForward);
    components.getDoubleLeftSolenoid().set(DoubleSolenoid.Value.kForward);
  }

  public void closePistons() {
    components.getDoubleRightSolenoid().close();
    components.getDoubleLeftSolenoid().close();
  }

  public RouletteColor getRequiredColorFromMatchColor() {
    RouletteColor requiredMatchColor;
    if (DriverStation.getInstance().getGameSpecificMessage().isEmpty()) {
      return null;
    }
    switch (DriverStation.getInstance().getGameSpecificMessage().charAt(0)) {
      case 'B':
        requiredMatchColor = RouletteColor.Blue;
        break;
      case 'G':
        requiredMatchColor = RouletteColor.Green;
        break;
      case 'R':
        requiredMatchColor = RouletteColor.Red;
        break;
      case 'Y':
        requiredMatchColor = RouletteColor.Yellow;
        break;
      default:
        requiredMatchColor = null;
        break;
    }
    return requiredMatchColor;
  }

  public RouletteColor getCurrentColor() {
    final Color detectedColor = components.getColorSensorV3().getColor();
    final RouletteColor[] rouletteColors = RouletteColor.values();
    Optional<RouletteColor> roulette = Arrays.stream(rouletteColors).max(
        Comparator.comparing(color -> color.getRgbValue().howCloseTo(detectedColor)));
    return roulette.orElse(null);
  }

  public void stopMotor() {
    moveRouletteBySpeed(0);
  }

  public void spinByColorsCount(final double requiredColorCount) {
    rouletteRotation = getRouletteRotationByColorCount(requiredColorCount);
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

  public double getColorCountRequiredToColor(final RouletteColor requiredColor) {
    int indexOfRequiredColor = rouletteColors.indexOf(requiredColor);
    int indexOfCurrentColor = rouletteColors.indexOf(getCurrentColor());
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
