package robot.shooter;

import static robot.shooter.ShooterConstants.*;
import static robot.shooter.ShooterConstants.LOADER_PORT;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BasicShooterComponents implements ShooterComponents {

  private final WPI_TalonSRX masterMotor;
  private final WPI_VictorSPX slaveMotor;
  private final WPI_TalonSRX loaderMotor;

  public BasicShooterComponents() {
    masterMotor = new WPI_TalonSRX(MASTER_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.configPeakCurrentLimit(PICK_AMP);
    masterMotor.configPeakCurrentDuration(PICK_AMP_DURATION);
    masterMotor.configContinuousCurrentLimit(CONTINUOS_CURRENT_LIMIT);
    masterMotor.setNeutralMode(NeutralMode.Brake);
    masterMotor.enableCurrentLimit(true);

    slaveMotor = new WPI_VictorSPX(SLAVE_PORT);
    slaveMotor.configFactoryDefault();
    slaveMotor.follow(masterMotor);

    loaderMotor = new WPI_TalonSRX(LOADER_PORT);
    loaderMotor.configFactoryDefault();
    loaderMotor.configAllSettings(getConfiguration());
    loaderMotor.configPeakCurrentLimit(PICK_AMP);
    loaderMotor.configPeakCurrentDuration(PICK_AMP_DURATION);
    loaderMotor.configContinuousCurrentLimit(CONTINUOS_CURRENT_LIMIT);
    loaderMotor.setNeutralMode(NeutralMode.Brake);
    loaderMotor.enableCurrentLimit(true);
  }

  private TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.slot0.kP = VELOCITY_P;
    config.slot0.kI = VELOCITY_I;
    config.slot0.kD = VELOCITY_D;
    config.slot0.kF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    return config;
  }

  private TalonSRXConfiguration getLoaderConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.slot0.kP = LOADER_VELOCITY_P;
    config.slot0.kI = LOADER_VELOCITY_I;
    config.slot0.kD = LOADER_VELOCITY_D;
    config.slot0.kF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
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

  @Override
  public IMotorController getLoaderMotor() {
    return loaderMotor;
  }
}
