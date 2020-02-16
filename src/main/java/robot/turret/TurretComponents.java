package robot.turret;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface TurretComponents {

  WPI_TalonSRX getMasterMotor();
}
