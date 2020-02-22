package robot.delayConveyor;

import static robot.delayConveyor.DelayConveyorConstants.DelayConveyorComponentsA.MASTER_MOTOR_PORT;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BasicDelayConveyorComponentsA implements DelayConveyorComponents {

  private final WPI_VictorSPX motor;

  public BasicDelayConveyorComponentsA() {
    motor = new WPI_VictorSPX(MASTER_MOTOR_PORT);
    motor.configFactoryDefault();
    motor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public WPI_VictorSPX getMotor() {
    return motor;
  }
}

