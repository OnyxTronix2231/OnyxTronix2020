package robot.roulette;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Roulette extends SubsystemBase {

    private final RouletteBasicComponents componentsRoulette;

    public Roulette(final RouletteBasicComponents componentsRoulette) {
        this.componentsRoulette = componentsRoulette;
    }

    public void spinMotor(final double speed) {
        componentsRoulette.getController().set(ControlMode.PercentOutput, speed);
    }

    public void openDoubleSolenoid() {
        componentsRoulette.getDoubleSolenoid().set(DoubleSolenoid.Value.kForward);

    }

    public void closeDoubleSolenoid() {
        componentsRoulette.getDoubleSolenoid().close();
    }

    public void stopSpin() {
        componentsRoulette.getController().set(ControlMode.Disabled, 1);
    }
}
