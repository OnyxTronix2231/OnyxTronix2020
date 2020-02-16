package robot.loaderConveyor;

import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.MASTER_MOTOR_PORT;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.MAX_CLOSED_LOOP_OUTPUT;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.MAX_VELOCITY;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.PEAK_AMP;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.PEAK_AMP_DURATION;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.SLAVE_MOTOR_PORT;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.VELOCITY_D;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.VELOCITY_I;
import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.VELOCITY_P;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class BasicLoaderConveyorComponentsA implements LoaderConveyorComponents {

    private final WPI_TalonSRX masterMotor;
    private final WPI_VictorSPX slaveMotor;

    public BasicLoaderConveyorComponentsA() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(true);
        masterMotor.setInverted(true);

        slaveMotor = new WPI_VictorSPX(SLAVE_MOTOR_PORT);
        slaveMotor.configFactoryDefault();
        slaveMotor.setNeutralMode(NeutralMode.Brake);
        slaveMotor.follow(masterMotor);
        slaveMotor.setInverted(true);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public IMotorController getSlaveMotor() {
        return slaveMotor;
    }

    private TalonSRXConfiguration getConfiguration() {
        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.slot0.kP = VELOCITY_P;
        config.slot0.kI = VELOCITY_I;
        config.slot0.kD = VELOCITY_D;
        config.slot0.kF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
        config.peakCurrentLimit = PEAK_AMP;
        config.peakCurrentDuration = PEAK_AMP_DURATION;
        config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
        return config;
    }
}
