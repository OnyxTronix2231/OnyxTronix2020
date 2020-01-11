package robot.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicTurretComponents implements TurretComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicTurretComponents(WPI_TalonSRX masterMotor) {
    this.masterMotor = masterMotor;
    masterMotor.configFactoryDefault();
  }


  @Override
  public WPI_TalonSRX getMasterMotor() {
    return null;
  }
}
