package robot.roulette;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static robot.roulette.RouletteConstants.*;

public class Roulette extends SubsystemBase {

    private final BasicRouletteComponents components;

    private final List <RouletteColor> rouletteColors;

    public Roulette(final BasicRouletteComponents components) {
        this.components = components;
        this.rouletteColors = Arrays.asList(RouletteColor.values());
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

        final Double[] howCloseToEach = {
          RouletteColor.Red.rgbValue.howCloseTo(detectedColor),
          RouletteColor.Green.rgbValue.howCloseTo(detectedColor),
          RouletteColor.Blue.rgbValue.howCloseTo(detectedColor),
          RouletteColor.Yellow.rgbValue.howCloseTo(detectedColor)
        };

        switch (Arrays.binarySearch(howCloseToEach, Collections.max(Arrays.asList(howCloseToEach)))) {
            case 0:
                return RouletteColor.Red;
            case 1:
                return RouletteColor.Green;
            case 2:
                return RouletteColor.Blue;
            default:
                return RouletteColor.Yellow;
        }
    }

    public void stopSpin() {
        components.getMasterMotor().set(0);
    }

    public void spinByColorsCount(final double PastColorCount) {
        double rouletteRotation = getRouletteRotationByColorCount(PastColorCount);
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
   
    public boolean isOnTarget() {
        return Math.abs(components.getMasterMotor().getClosedLoopError()) <= TOLERANCE;
    }
}
