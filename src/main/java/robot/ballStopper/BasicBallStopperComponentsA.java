package robot.ballStopper;

import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.MASTER_MOTOR_PORT;
import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.PEAK_AMP;
import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.PEAK_AMP_DURATION;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicBallStopperComponentsA implements BallStopperComponents {

  private final WPI_TalonSRX motor;

  public BasicBallStopperComponentsA() {
    motor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    motor.configFactoryDefault();
    motor.configAllSettings(getConfiguration());
    motor.setNeutralMode(NeutralMode.Brake);
    motor.enableCurrentLimit(true);
  }

  private TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = PEAK_AMP;
    config.peakCurrentDuration = PEAK_AMP_DURATION;
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
    return config;
  }

  @Override
  public WPI_TalonSRX getMotor() {
    return motor;
  }
}
