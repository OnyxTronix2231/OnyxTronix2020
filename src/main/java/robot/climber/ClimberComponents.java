package robot.climber;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface ClimberComponents {

  WPI_TalonSRX getMasterMotor();

  IMotorController getSlaveMotor();
}
