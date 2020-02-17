package robot.ballStopper;

import static robot.ballStopper.BallStopperConstants.BallStopperComponentsA.MASTER_MOTOR_PORT;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BasicBallStopperComponentsA implements BallStopperComponents {

  private final WPI_VictorSPX motor;

  public BasicBallStopperComponentsA() {
    motor = new WPI_VictorSPX(MASTER_MOTOR_PORT);
    motor.configFactoryDefault();
    motor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public WPI_VictorSPX getMotor() {
    return motor;
  }
}
