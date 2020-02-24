package robot.storageConveyor;

import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.PEAK_AMP;
import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.PEAK_AMP_DURATION;
import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.MASTER_MOTOR_PORT;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicStorageConveyorComponentsA implements StorageConveyorComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicStorageConveyorComponentsA() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.setNeutralMode(NeutralMode.Brake);
  }

  private TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = PEAK_AMP;
    config.peakCurrentDuration = PEAK_AMP_DURATION;
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
    return config;}

  @Override
  public IMotorController getMasterMotor() {
    return masterMotor;
  }
}
