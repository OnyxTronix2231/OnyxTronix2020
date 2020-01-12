package robot.roulette;

import java.util.Arrays;
import java.util.Collections;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Roulette extends SubsystemBase {

    private final BasicRouletteComponents components;

    public Roulette(final BasicRouletteComponents componentsRoulette) {
        this.components = componentsRoulette;
    }

    public void spinMotor(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public void openDoubleSolenoid() {
        components.getDoubleSolenoid().set(DoubleSolenoid.Value.kForward);
    }

    public void closeDoubleSolenoid() {
        components.getDoubleSolenoid().close();
    }

    public RouletteColor getClosestColor() {
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
        components.getMasterMotor().set(ControlMode.Disabled, 1);
    }
}
