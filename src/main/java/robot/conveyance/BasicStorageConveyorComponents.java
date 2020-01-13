package robot.conveyance;

import static robot.conveyance.ConveyorConstants.FIRST_MASTER_MOTOR_PORT;
import static robot.conveyance.ConveyorConstants.FIRST_SLAVE_MOTOR_PORT;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicStorageConveyorComponents implements ConveyorComponents {

  private final WPI_TalonSRX masterMotor;
  private final WPI_TalonSRX slaveMotor;

  public BasicStorageConveyorComponents() {
    masterMotor = new WPI_TalonSRX(FIRST_MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();

    slaveMotor = new WPI_TalonSRX(FIRST_SLAVE_MOTOR_PORT);
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
