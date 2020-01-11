package robot.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface TurretComponents {
  public WPI_TalonSRX getMasterMotor();
}
