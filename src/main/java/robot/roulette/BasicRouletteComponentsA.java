package robot.roulette;

import static robot.roulette.RouletteConstants.RouletteComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.roulette.RouletteConstants.RouletteComponentsA.DOUBLE_SOLENOID_FORWARD_CHANNEL;
import static robot.roulette.RouletteConstants.RouletteComponentsA.DOUBLE_SOLENOID_REVERSE_CHANNEL;
import static robot.roulette.RouletteConstants.RouletteComponentsA.MASTER_MOTOR_PORT;
import static robot.roulette.RouletteConstants.RouletteComponentsA.MAX_ACCELERATION;
import static robot.roulette.RouletteConstants.RouletteComponentsA.MAX_VELOCITY;
import static robot.roulette.RouletteConstants.RouletteComponentsA.MOTION_CURVE_STRENGTH;
import static robot.roulette.RouletteConstants.RouletteComponentsA.PEAK_AMP;
import static robot.roulette.RouletteConstants.RouletteComponentsA.PEAK_AMP_DURATION;
import static robot.roulette.RouletteConstants.RouletteComponentsA.VELOCITY_D;
import static robot.roulette.RouletteConstants.RouletteComponentsA.VELOCITY_I;
import static robot.roulette.RouletteConstants.RouletteComponentsA.VELOCITY_P;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;

public class BasicRouletteComponentsA implements RouletteComponents {

  private final WPI_TalonSRX masterMotor;
  private final DoubleSolenoid doubleSolenoid;
  private final ColorSensorV3 colorSensor;

  public BasicRouletteComponentsA() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.setNeutralMode(NeutralMode.Brake);
    masterMotor.enableCurrentLimit(true);
    masterMotor.setSensorPhase(true);

    doubleSolenoid = new DoubleSolenoid
        (DOUBLE_SOLENOID_FORWARD_CHANNEL, DOUBLE_SOLENOID_REVERSE_CHANNEL);

    colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
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
  public DoubleSolenoid getDoubleSolenoid() {
    return doubleSolenoid;
  }

  @Override
  public ColorSensorV3 getColorSensor() {
    return colorSensor;
  }
}
