package robot.roulette;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;

import static robot.roulette.RouletteConstants.RouletteComponentsA.*;

public class BasicARouletteComponents implements RouletteComponents {

    private final WPI_TalonSRX masterMotor;
    private final DoubleSolenoid doubleRightSolenoid;
    private final DoubleSolenoid doubleLeftSolenoid;
    private final ColorSensorV3 colorSensorV3;

    public BasicARouletteComponents() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getConfiguration());
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(true);
        masterMotor.setSensorPhase(true);

        doubleLeftSolenoid = new DoubleSolenoid
                (DOUBLE_LEFT_SOLENOID_FORWARD_CHANNEL, DOUBLE_LEFT_SOLENOID_REVERSE_CHANNEL);

        doubleRightSolenoid = new DoubleSolenoid
                (DOUBLE_RIGHT_SOLENOID_FORWARD_CHANNEL, DOUBLE_RIGHT_SOLENOID_REVERSE_CHANNEL);

        colorSensorV3 = new ColorSensorV3(I2C.Port.kOnboard);
    }

    private TalonSRXConfiguration getConfiguration() {
        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.slot0.kP = VELOCITY_P;
        config.slot0.kI = VELOCITY_I;
        config.slot0.kD = VELOCITY_D;
        config.motionCruiseVelocity = MAX_VELOCITY;
        config.motionAcceleration = MAX_ACCELERATION;
        config.motionCurveStrength = MOTION_CURVE_STRENGTH;
        config.peakCurrentLimit = PEAK_AMP;
        config.peakCurrentDuration = PEAK_AMP_DURATION;
        config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
        return config;
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public DoubleSolenoid getDoubleRightSolenoid() {
        return doubleRightSolenoid;
    }

    @Override
    public DoubleSolenoid getDoubleLeftSolenoid() {
        return doubleLeftSolenoid;
    }

    @Override
    public ColorSensorV3 getColorSensorV3() {
        return colorSensorV3;
    }
}
