package robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface ShooterComponents {

  WPI_TalonSRX getMasterMotor();
  
  IMotorController getSlaveMotor();

  IMotorController getLoaderMotor();
}
