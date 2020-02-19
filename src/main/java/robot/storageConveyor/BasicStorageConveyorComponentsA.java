package robot.storageConveyor;

import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.MASTER_MOTOR_PORT;
import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.PEAK_AMP;
import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.PEAK_AMP_DURATION;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BasicStorageConveyorComponentsA implements StorageConveyorComponents {

  private final WPI_VictorSPX masterMotor;

  public BasicStorageConveyorComponentsA() {
    masterMotor = new WPI_VictorSPX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public IMotorController getMasterMotor() {
    return masterMotor;
  }
}
