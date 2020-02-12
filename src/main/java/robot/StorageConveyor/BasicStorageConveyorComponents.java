package robot.StorageConveyor;

import static robot.StorageConveyor.StorageConveyorConstants.StorageConveyorComponents.CONTINUOUS_CURRENT_LIMIT;
import static robot.StorageConveyor.StorageConveyorConstants.StorageConveyorComponents.MASTER_MOTOR_PORT;
import static robot.StorageConveyor.StorageConveyorConstants.StorageConveyorComponents.PEAK_AMP;
import static robot.StorageConveyor.StorageConveyorConstants.StorageConveyorComponents.PEAK_AMP_DURATION;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicStorageConveyorComponents implements StorageConveyorComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicStorageConveyorComponents() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.setNeutralMode(NeutralMode.Brake);
    masterMotor.enableCurrentLimit(true);
  }

  public TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
    config.peakCurrentDuration = PEAK_AMP_DURATION;
    config.peakCurrentLimit = PEAK_AMP;
    return config;
  }

  @Override
  public WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }
}
