package robot.storageConveyor;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface StorageConveyorComponents {

  WPI_TalonSRX getMasterMotor();

  IMotorController getSlaveMotor();
}
