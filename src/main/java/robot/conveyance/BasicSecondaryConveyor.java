package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicSecondaryConveyor implements ConveyorComponents {

  private final WPI_TalonSRX masterMotor;
  private final WPI_TalonSRX slaveMotor;

  public BasicSecondaryConveyor() {
    this.masterMotor = new WPI_TalonSRX(ConveyorConstants.SECOND_MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();

    this.slaveMotor = new WPI_TalonSRX(ConveyorConstants.SECOND_SLAVE_MOTOR_PORT);
    slaveMotor.configFactoryDefault();
    slaveMotor.follow(masterMotor);
  }

  @Override
  public final WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }

  @Override
  public IMotorController getSlaveMotor() {
    return slaveMotor;
  }
}
