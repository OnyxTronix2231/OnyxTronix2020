package robot.roulette;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;

import static robot.roulette.RouletteConstants.*;
import static robot.roulette.RouletteConstants.RobotAComponents.*;

public class BasicARouletteComponents implements RouletteComponents {

    private final WPI_TalonSRX masterMotor;
    private final DoubleSolenoid doubleRightSolenoid;
    private final DoubleSolenoid doubleLeftSolenoid;
    private final ColorSensorV3 colorSensorV3;

    public BasicARouletteComponents() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();
        masterMotor.configAllSettings(getConfiguration());
        masterMotor.configPeakCurrentLimit(PICK_AMP);
        masterMotor.configPeakCurrentDuration(PICK_AMP_DURATION);
        masterMotor.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(true);
        masterMotor.setSensorPhase(true);

        doubleLeftSolenoid = new DoubleSolenoid
                (DOUBLE_LEFT_SOLENOID_FORWARD_CHANNEL, DOUBLE_LEFT_SOLENOID_REVERSE_CHANNEL);

        doubleRightSolenoid = new DoubleSolenoid
                (DOUBLE_RIGHT_SOLENOID_FORWARD_CHANNEL, DOUBLE_RIGHT_SOLENOID_REVERSE_CHANNEL);

        colorSensorV3 = new ColorSensorV3(I2C.Port.kOnboard);
        new Compressor(0).stop();
    }

    private TalonSRXConfiguration getConfiguration() {
        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.slot0.kP = K_P;
        config.slot0.kI = K_I;
        config.slot0.kD = K_D;
        config.motionCruiseVelocity = MAX_VELOCITY;
        config.motionAcceleration = MAX_ACCELERATION;
        config.motionCurveStrength = MOTION_CURVE_STRENGTH;
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
