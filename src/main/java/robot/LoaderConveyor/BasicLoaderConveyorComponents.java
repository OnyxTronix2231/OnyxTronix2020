package robot.LoaderConveyor;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.LoaderConveyor.LoaderConveyorConstants.*;


public class BasicLoaderConveyorComponents implements LoaderConveyorComponents {

    private final WPI_TalonSRX motor;

    public BasicLoaderConveyorComponents() {
        motor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
        motor.configFactoryDefault();
        motor.configAllSettings(getConfiguration());
        motor.setNeutralMode(NeutralMode.Brake);
        motor.enableCurrentLimit(true);
    }

    private TalonSRXConfiguration getConfiguration() {
        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.slot0.kP = VELOCITY_P;
        config.slot0.kI = VELOCITY_I;
        config.slot0.kD = VELOCITY_D;
        config.slot0.kF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
        config.peakCurrentLimit = PICK_AMP;
        config.peakCurrentDuration = PICK_AMP_DURATION;
        return config;
    }

    @Override
    public final WPI_TalonSRX getMotor() {
        return motor;
    }
}
