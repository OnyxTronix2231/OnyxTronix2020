package robot.turret;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicTurretComponents implements TurretComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicTurretComponents() {
    this.masterMotor = new WPI_TalonSRX(TurretConstants.MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
  }
  
  @Override
  public WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }

  private TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.slot0.kP = TurretConstants.VELOCITY_P;
    config.slot0.kI = TurretConstants.VELOCITY_I;
    config.slot0.kD = TurretConstants.VELOCITY_D;
    config.slot0.kF = TurretConstants.VELOCITY_F;
    return config;
  }
}
