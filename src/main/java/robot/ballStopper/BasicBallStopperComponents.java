package robot.ballStopper;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.ballStopper.BallStopperConstants.BallStopperComponents.*;

public class BasicBallStopperComponents implements BallStopperComponents {

  private final WPI_TalonSRX motor;

  public BasicBallStopperComponents() {
    motor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    motor.configFactoryDefault();
    motor.setNeutralMode(NeutralMode.Brake);
    motor.enableCurrentLimit(true);
    motor.configAllSettings(getConfiguration());
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

