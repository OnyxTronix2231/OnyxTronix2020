package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class TalonFXVelocityController extends VelocityController {

    private final WPI_TalonFX motor;

    public TalonFXVelocityController(final int pidSlot, final double maxVelocity, final WPI_TalonFX motor) {
        super(pidSlot, maxVelocity, motor);
        this.motor = motor;
    }

    @Override
    public double get() {
        return motor.get();
    }
}
