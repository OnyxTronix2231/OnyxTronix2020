package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonSRXVelocityController extends VelocityController {

    private final WPI_TalonSRX motor;

    public TalonSRXVelocityController(int pidSlot, double maxVelocity, WPI_TalonSRX motor) {
        super(pidSlot, maxVelocity, motor);
        this.motor = motor;
    }

    @Override
    public double get() {
        return motor.get();
    }
}
