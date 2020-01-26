package robot.roulette;

import java.util.*;
import java.util.function.DoubleSupplier;
import java.util.function.ToDoubleFunction;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import javax.swing.*;

import static robot.roulette.RouletteConstants.*;

public class Roulette extends SubsystemBase {

    private final RouletteComponents components;
    private final List <RouletteColor> rouletteColors;

    public Roulette(final RouletteComponents components) {
        this.components = components;
        this.rouletteColors = Arrays.asList(RouletteColor.values());
        Shuffleboard.getTab("Roulette").addString("", () -> getCurrentColor().toString());
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
        switch (DriverStation.getInstance().getGameSpecificMessage().charAt(0)) {
            case 'B':
                requiredMatchColor = RouletteColor.Red;
                break;
            case 'G':
                requiredMatchColor = RouletteColor.Yellow;
                break;
            case 'R':
                requiredMatchColor = RouletteColor.Blue;
                break;
            case 'Y':
                requiredMatchColor = RouletteColor.Green;
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
//                return Arrays.stream(rouletteColors).max(
//                Comparator.comparing(color -> color.getRgbValue().howCloseTo(detectedColor))).orElse(null);
    }

    public void stopSpin() {
        components.getMasterMotor().set(0);
    }

    public void spinByColorsCount(final double pastColorCount) {
        double rouletteRotation = getRouletteRotationByColorCount(pastColorCount);
        components.getMasterMotor().set(ControlMode.MotionMagic,
                getRouletteRotationToEncoderUnits(rouletteRotation));
    }

    public double getRouletteRotationToEncoderUnits(final double rouletteRotations) {
        return rouletteRotations * RouletteConstants.ENCODER_UNITS_PER_ROTATION * ROULETTE_ROTATION_TO_WHEEL_ROTATION;
    }

    public double getRouletteRotationByColorCount(final double colorCount) {
       return (double) (MIN_ROTATIONS * COLORS_IN_ROTATIONS - colorCount) / COLORS_IN_ROTATIONS;
    }

    /**
        @return When return value is positive, the roulette needs to be rotated clockwise
     */

    public double getColorCountRequiredToColor(final RouletteColor requiredColor) {
        int indexOfRequiredColor = rouletteColors.indexOf(requiredColor);
        int indexOfCurrentColor = rouletteColors.indexOf(getCurrentColor());
        int colorDistance = indexOfRequiredColor - indexOfCurrentColor;
        colorDistance = Math.abs(colorDistance);
        return colorDistance - DISTANCE_FROM_FIELD_SENSOR;
    }

    public void resetEncoder(){
        components.getMasterMotor().setSelectedSensorPosition(0);
    }

    public boolean isOnTarget() {
        return Math.abs(components.getMasterMotor().getClosedLoopError()) <= TOLERANCE;
    }
}
