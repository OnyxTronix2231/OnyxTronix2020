package robot.roulette;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class RouletteBasicComponents implements RouletteComponents {

    private final TalonSRX controller;
    private final DoubleSolenoid doubleSolenoid;

    public RouletteBasicComponents() {
        controller = new TalonSRX(0);
        doubleSolenoid = new DoubleSolenoid(0, 1);
    }

    @Override
    public TalonSRX getController() {
        return controller;
    }

    @Override
    public DoubleSolenoid getDoubleSolenoid() {
        return doubleSolenoid;
    }
}
