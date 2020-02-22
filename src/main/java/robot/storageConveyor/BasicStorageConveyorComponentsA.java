package robot.storageConveyor;

import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.MASTER_MOTOR_PORT;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BasicStorageConveyorComponentsA implements StorageConveyorComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicStorageConveyorComponentsA() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public IMotorController getMasterMotor() {
    return masterMotor;
  }
}
