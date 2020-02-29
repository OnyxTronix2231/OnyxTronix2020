package robot.ballStopper;

import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.BALL_STOPPER_MOTOR_PORT;
import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.BALL_STOPPER_DELAYED_MOTOR_PORT;
import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.PEAK_AMP;
import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.PEAK_AMP_DURATION;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicBallStopperComponentsA implements BallStopperComponents {

  private final WPI_TalonSRX ballStopperMotor;
  private final WPI_TalonSRX delayMotor;

  public BasicBallStopperComponentsA() {

    ballStopperMotor = new WPI_TalonSRX(BALL_STOPPER_MOTOR_PORT);
    ballStopperMotor.configFactoryDefault();
    ballStopperMotor.setNeutralMode(NeutralMode.Brake);
    ballStopperMotor.configAllSettings(getConfigurationA());
    ballStopperMotor.enableCurrentLimit(true);

    delayMotor = new WPI_TalonSRX(BALL_STOPPER_DELAYED_MOTOR_PORT);
    delayMotor.configFactoryDefault();
    delayMotor.setNeutralMode(NeutralMode.Brake);
    delayMotor.configAllSettings(getConfigurationA());
    delayMotor.enableCurrentLimit(true);
    delayMotor.setInverted(true);
  }

  private TalonSRXConfiguration getConfigurationA() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = PEAK_AMP;
    config.peakCurrentDuration = PEAK_AMP_DURATION;
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
    config.forwardLimitSwitchNormal = LimitSwitchNormal.Disabled;
    config.reverseLimitSwitchNormal = LimitSwitchNormal.Disabled;
    return config;
  }

  @Override
  public IMotorController getBallStopperMotor() {
    return ballStopperMotor;
  }

  @Override
  public IMotorController getDelayMotor() {
    return delayMotor;
  }
}
