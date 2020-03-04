package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.BALL_COLLECTOR_RAMP;
import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.SOLENOID_PORT;
import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.MASTER_MOTOR_PORT;
import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.PEAK_AMP;
import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.PEAK_AMP_DURATION;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

public class BasicBallCollectorComponentsA implements BallCollectorComponents {

  private final WPI_TalonSRX masterMotor;
  private final Solenoid solenoid;

  public BasicBallCollectorComponentsA() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.enableCurrentLimit(true);
    masterMotor.setNeutralMode(NeutralMode.Brake);
    masterMotor.setInverted(true);

    solenoid = new Solenoid(SOLENOID_PORT);
  }

  public TalonSRXConfiguration getConfiguration() {
    final TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = PEAK_AMP;
    config.peakCurrentDuration = PEAK_AMP_DURATION;
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
    config.openloopRamp = BALL_COLLECTOR_RAMP;
    return config;
  }

  @Override
  public WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }

  @Override
  public Solenoid getSolenoid() {
    return solenoid;
  }
}
