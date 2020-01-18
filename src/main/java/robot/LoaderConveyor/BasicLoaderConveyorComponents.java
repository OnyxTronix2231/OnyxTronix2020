package robot.LoaderConveyor;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.LoaderConveyor.LoaderConveyorConstants.*;


public class BasicLoaderConveyorComponents implements LoaderConveyorComponents {

    private final WPI_TalonSRX motor;
    private final Config config;

    public BasicLoaderConveyorComponents(Config config) {
        this.config = config;
        motor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
        motor.configFactoryDefault();
        motor.configAllSettings(config.getConfiguration());
        motor.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
        motor.configPeakCurrentDuration(PICK_AMP_DURATION);
        motor.configPeakCurrentLimit(PICK_AMP);
        motor.setNeutralMode(NeutralMode.Brake);
        motor.enableCurrentLimit(true);
    }

    @Override
    public final WPI_TalonSRX getMotor() {
        return motor;
    }
}
