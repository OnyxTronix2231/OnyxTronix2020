package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicConveyorComponents implements ConveyorComponents {

  private final WPI_TalonSRX masterMotor;
  private final WPI_TalonSRX slaveMotor;

  public BasicConveyorComponents() {
    masterMotor = new WPI_TalonSRX(ConveyorConstants.FIRST_MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();

    slaveMotor = new WPI_TalonSRX(ConveyorConstants.FIRST_SLAVE_MOTOR_PORT);
    slaveMotor.configFactoryDefault();
    slaveMotor.follow(masterMotor);
  }

  @Override
  public WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }

  @Override
  public IMotorController getSlaveMotor() {
    return slaveMotor;
  }
}
