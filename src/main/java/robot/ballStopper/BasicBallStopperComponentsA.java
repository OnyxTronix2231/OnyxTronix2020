package robot.ballStopper;

import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.LEFT_MOTOR_PORT;
import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.RIGHT_MOTOR_PORT;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BasicBallStopperComponentsA implements BallStopperComponents {

  private final WPI_VictorSPX leftMotor;
  private final WPI_VictorSPX rightMotor;

  public BasicBallStopperComponentsA() {
    leftMotor = new WPI_VictorSPX(LEFT_MOTOR_PORT);
    leftMotor.configFactoryDefault();
    leftMotor.setNeutralMode(NeutralMode.Brake);

    rightMotor = new WPI_VictorSPX(RIGHT_MOTOR_PORT);
    rightMotor.configFactoryDefault();
    rightMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public WPI_VictorSPX getLeftMotor() {
    return leftMotor;
  }

  @Override
  public WPI_VictorSPX getRightMotor() {
    return rightMotor;
  }
}
