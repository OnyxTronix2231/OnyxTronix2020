package robot.shooter;

import static robot.shooter.ShooterConstants.ShooterComponentsA.CLOSE_LOOP_RAMP;
import static robot.shooter.ShooterConstants.ShooterComponentsA.CURRENT_LIMIT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.MASTER_PORT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.OPEN_LOOP_RAMP;
import static robot.shooter.ShooterConstants.ShooterComponentsA.SLAVE_PORT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.SOLENOID_PORT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.TRIGGER_THRESHOLD_CURRENT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.TRIGGER_THRESHOLD_TIME;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_D;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_F;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_I;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_P;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;

public class BasicShooterComponentsA implements ShooterComponents {

  private final WPI_TalonFX masterMotor;
  private final WPI_TalonFX slaveMotor;
  private final Solenoid solenoid;

  public BasicShooterComponentsA() {
    masterMotor = new WPI_TalonFX(MASTER_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getFalconConfiguration());
    masterMotor.setNeutralMode(NeutralMode.Coast);
    masterMotor.setInverted(true);

    slaveMotor = new WPI_TalonFX(SLAVE_PORT);
    slaveMotor.configAllSettings(getFalconConfiguration());
    slaveMotor.configFactoryDefault();
    slaveMotor.setNeutralMode(NeutralMode.Coast);
    slaveMotor.follow(masterMotor);

    solenoid = new Solenoid(SOLENOID_PORT);
  }


  private TalonFXConfiguration getFalconConfiguration() {
    final TalonFXConfiguration config = new TalonFXConfiguration();
    config.slot0.kP = VELOCITY_P;
    config.slot0.kI = VELOCITY_I;
    config.slot0.kD = VELOCITY_D;
    config.slot0.kF = VELOCITY_F;
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    config.supplyCurrLimit.currentLimit = CURRENT_LIMIT;
    config.supplyCurrLimit.triggerThresholdCurrent = TRIGGER_THRESHOLD_CURRENT;
    config.supplyCurrLimit.triggerThresholdTime = TRIGGER_THRESHOLD_TIME;
    config.closedloopRamp = CLOSE_LOOP_RAMP;
    config.openloopRamp = OPEN_LOOP_RAMP;
    config.supplyCurrLimit.enable = true;
    return config;
  }

  @Override
  public WPI_TalonFX getMasterMotor() {
    return masterMotor;
  }

  @Override
  public WPI_TalonFX getSlaveMotor() {
    return slaveMotor;
  }

  @Override
  public Solenoid getSolenoid() {
    return solenoid;
  }
}
