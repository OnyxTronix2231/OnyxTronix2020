package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicSecondaryConveyance implements ConveyanceComponents{

  private final WPI_TalonSRX masterMotor;
  private final WPI_TalonSRX slaveMotor;

  public BasicSecondaryConveyance(){
    this.masterMotor = new WPI_TalonSRX(ConveyanceConstants.SECOND_MASTER_MOTOR_PORT);

    this.slaveMotor = new WPI_TalonSRX(ConveyanceConstants.SECOND_SLAVE_MOTOR_PORT);
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
