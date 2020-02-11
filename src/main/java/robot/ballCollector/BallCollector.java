package robot.ballCollector;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallCollector extends SubsystemBase {

    private final BallCollectorComponents components;

    public BallCollector(final BallCollectorComponents components) {
        this.components = components;
    }

    public final void collectBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public final void stopMotor() {
        collectBySpeed(0);
    }

    public void openPistons() {
        components.getDoubleSolenoid().set(DoubleSolenoid.Value.kForward);
    }

    public void closePistons() {
        components.getDoubleSolenoid().set(DoubleSolenoid.Value.kReverse);
    }
}
