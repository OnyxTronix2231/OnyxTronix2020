package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicSecondaryConveyance implements ConveyanceComponents{

  private final WPI_TalonSRX secondMasterMotor;
  private final WPI_TalonSRX secondSlaveMotor;

  public BasicSecondaryConveyance(){
    this.secondMasterMotor = new WPI_TalonSRX(ConveyanceConstants.SECOND_MASTER_MOTOR_PORT);
    this.secondSlaveMotor = new WPI_TalonSRX(ConveyanceConstants.SECOND_SLAVE_MOTOR_PORT);
    secondSlaveMotor.follow(secondMasterMotor);
}

  @Override
  public final WPI_TalonSRX getMasterMotor() {
    return secondMasterMotor;
  }

  @Override
  public IMotorController getSlaveMotor() {
    return secondSlaveMotor;
  }
}
