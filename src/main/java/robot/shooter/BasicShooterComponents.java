package robot.shooter;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BasicShooterComponents implements ShooterComponents {

  private final WPI_TalonSRX masterMotor;
  private final WPI_VictorSPX slaveMotor;

  public BasicShooterComponents() {
    masterMotor = new WPI_TalonSRX(ShooterConstants.MASTER_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());

    slaveMotor = new WPI_VictorSPX(ShooterConstants.SLAVE_PORT);
    slaveMotor.configFactoryDefault();
    slaveMotor.follow(masterMotor);
  }

  private TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.slot0.kP = ShooterConstants.VELOCITY_P;
    config.slot0.kI = ShooterConstants.VELOCITY_I;
    config.slot0.kD = ShooterConstants.VELOCITY_D;
    config.slot0.kF = ShooterConstants.VELOCITY_F;
    return config;
  }

  @Override
  public WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }

  @Override
  public IMotorController getSlaveMotor() {
    return slaveMotor;
  }
}
