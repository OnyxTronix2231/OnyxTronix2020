package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonSRXVelocityController extends VelocityController {

    private final WPI_TalonSRX motor;

    public TalonSRXVelocityController(double maxVelocity, int pidSlot, WPI_TalonSRX motor) {
        super(maxVelocity, pidSlot, motor);
        this.motor = motor;
    }

    @Override
    public double get() {
        return motor.get();
    }
}
