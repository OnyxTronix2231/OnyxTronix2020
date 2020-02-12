package robot.roulette;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.DoubleSupplier;

import static robot.roulette.RouletteConstants.*;

public class Roulette extends SubsystemBase {

    private final RouletteComponents components;
    private final List<RouletteColor> rouletteColors;
    double encoderSetpoint = 0;
    double rouletteRotation = 0;

    public Roulette(final RouletteComponents components) {
        this.components = components;
        this.rouletteColors = Arrays.asList(RouletteColor.values());
        Shuffleboard.getTab("Roulette").addString("Current color", () -> getCurrentColor().toString());
        Shuffleboard.getTab("Roulette").addNumber("Req color", this::getS);
        Shuffleboard.getTab("Roulette").addNumber("Encoder Setpoint", () -> encoderSetpoint);
        Shuffleboard.getTab("Roulette").addNumber("Color count", () -> rouletteRotation);
    }

    private double getS() {
        if (getRequiredColorFromMatchColor() == null) {
            return 0;
        }
        return getColorCountRequiredToColor(getRequiredColorFromMatchColor());
    }

    public void spinMotor(final DoubleSupplier speedSupplier) {
        components.getMasterMotor().set(speedSupplier.getAsDouble());
    }

    public void openDoubleSolenoid() {
        components.getDoubleRightSolenoid().set(DoubleSolenoid.Value.kForward);
        components.getDoubleLeftSolenoid().set(DoubleSolenoid.Value.kForward);
    }

    public void closeDoubleSolenoid() {
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

    public void stopSpin() {
        components.getMasterMotor().set(0);
    }

    public void spinByColorsCount(final double requiredColorCount) {
        rouletteRotation = getRouletteRotationByColorCount(requiredColorCount);
        encoderSetpoint = getRouletteRotationToEncoderUnits(rouletteRotation);
        components.getMasterMotor().set(ControlMode.Position, encoderSetpoint +
                components.getMasterMotor().getSelectedSensorPosition());
    }

    public double getRouletteRotationToEncoderUnits(final double rouletteRotations) {
        return rouletteRotations * RouletteConstants.ENCODER_UNITS_PER_ROTATION * ROULETTE_ROTATION_TO_WHEEL_ROTATION;
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
        int colorDistance = indexOfRequiredColor - indexOfCurrentColor;
        colorDistance = Math.abs(colorDistance);
        return colorDistance - DISTANCE_FROM_FIELD_SENSOR;
    }

    public void resetEncoder() {
        components.getMasterMotor().setSelectedSensorPosition(0);
    }

    public boolean isOnTarget() {
        return Math.abs(components.getMasterMotor().getClosedLoopError()) <= TOLERANCE;
    }
}
