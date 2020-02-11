package robot.loaderConveyor;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponents.*;

public class BasicLoaderConveyorComponents implements robot.loaderConveyor.LoaderConveyorComponents {

    private final WPI_TalonSRX masterMotor;
    private final WPI_TalonSRX slaveMotor;

    public BasicLoaderConveyorComponents() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(true);
        masterMotor.setInverted(true);

        slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR_PORT);
        slaveMotor.configFactoryDefault();
        slaveMotor.configAllSettings(getConfiguration());
        slaveMotor.setNeutralMode(NeutralMode.Brake);
        slaveMotor.enableCurrentLimit(true);
        slaveMotor.follow(masterMotor);
        slaveMotor.setInverted(true);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public WPI_TalonSRX getSlaveMotor() {
        return slaveMotor;
    }

    private TalonSRXConfiguration getConfiguration() {
        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.slot0.kP = VELOCITY_P;
        config.slot0.kI = VELOCITY_I;
        config.slot0.kD = VELOCITY_D;
        config.slot0.kF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        config.peakCurrentLimit = PICK_AMP;
        config.peakCurrentDuration = PICK_AMP_DURATION;
        config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
        return config;
    }
}
