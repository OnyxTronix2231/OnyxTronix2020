package robot.ballStopper;

import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.BALL_STOPPER_MOTOR_PORT;
import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.BALL_STOPPER_DELAYED_MOTOR_PORT;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BasicBallStopperComponentsA implements BallStopperComponents {

  private final WPI_TalonSRX ballStopperMotor;
  private final WPI_TalonSRX delayMotor;

  public BasicBallStopperComponentsA() {
    ballStopperMotor = new WPI_TalonSRX(BALL_STOPPER_MOTOR_PORT);
    ballStopperMotor.configFactoryDefault();
    ballStopperMotor.setNeutralMode(NeutralMode.Brake);

    delayMotor = new WPI_TalonSRX(BALL_STOPPER_DELAYED_MOTOR_PORT);
    delayMotor.configFactoryDefault();
    delayMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public WPI_TalonSRX getBallStopperMotor() {
    return ballStopperMotor;
  }

  @Override
  public WPI_TalonSRX getDelayMotor() {
    return delayMotor;
  }
}
