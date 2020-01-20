package robot.roulette;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static robot.roulette.RouletteConstants.*;

public class Roulette extends SubsystemBase {

    private final BasicRouletteComponents components;

    public Roulette(final BasicRouletteComponents componentsRoulette) {
        this.components = componentsRoulette;
    }

    public void spinMotor(final DoubleSupplier speed) {
        components.getMasterMotor().set(speed.getAsDouble());
    }

    public void openDoubleSolenoid() {
        components.getDoubleSolenoid().set(DoubleSolenoid.Value.kForward);
    }

    public void closeDoubleSolenoid() {
        components.getDoubleSolenoid().close();
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

    public void spinByColorsCount(int PastColorCount) {
        double rouletteRotation = getRouletteRotationByColorCount(PastColorCount);
        components.getMasterMotor().set(ControlMode.MotionMagic,
                getRouletteRotationToEncoderUnits(rouletteRotation));
    }

    public double getRouletteRotationToEncoderUnits(double rouletteRotations){
        return rouletteRotations * RouletteConstants.ENCODER_UNITS_PER_ROTATION * ROULETTE_ROTATION_TO_WHEEL_ROTATION;
    }

    public double getRouletteRotationByColorCount(int colorCount){
       return (double) (MIN_ROTATIONS * COLORS_IN_ROTATIONS - colorCount) / COLORS_IN_ROTATIONS;
    }

    public boolean isOnTarget() {
        return Math.abs(components.getMasterMotor().getClosedLoopError()) <= TOLERANCE;
    }
}
