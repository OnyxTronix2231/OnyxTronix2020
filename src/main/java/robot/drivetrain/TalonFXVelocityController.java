package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class TalonFXVelocityController extends VelocityController {

    private final WPI_TalonFX motor;

    public TalonFXVelocityController(final double maxVelocity, final int pidSlot, final WPI_TalonFX motor) {
        super(maxVelocity, pidSlot, motor);
        this.motor = motor;
    }

    @Override
    public double get() {
        return motor.get();
    }
}
