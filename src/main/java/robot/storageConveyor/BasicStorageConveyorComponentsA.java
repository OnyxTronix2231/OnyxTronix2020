package robot.storageConveyor;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.*;

public class BasicStorageConveyorComponentsA implements StorageConveyorComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicStorageConveyorComponentsA() {
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
