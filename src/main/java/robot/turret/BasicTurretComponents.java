package robot.turret;

import static robot.turret.TurretConstants.CONTINUOUS_CURRENT_LIMIT;
import static robot.turret.TurretConstants.MASTER_MOTOR_PORT;
import static robot.turret.TurretConstants.MAX_ACCELERATION;
import static robot.turret.TurretConstants.MAX_VELOCITY;
import static robot.turret.TurretConstants.PEAK_AMP;
import static robot.turret.TurretConstants.PEAK_AMP_DURATION;
import static robot.turret.TurretConstants.VELOCITY_D;
import static robot.turret.TurretConstants.VELOCITY_F;
import static robot.turret.TurretConstants.VELOCITY_I;
import static robot.turret.TurretConstants.VELOCITY_P;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicTurretComponents implements TurretComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicTurretComponents() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.enableCurrentLimit(true);
    masterMotor.setNeutralMode(NeutralMode.Brake);
  }
  
  private TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.slot0.kP = VELOCITY_P;
    config.slot0.kI = VELOCITY_I;
    config.slot0.kD = VELOCITY_D;
    config.slot0.kF = VELOCITY_F;
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;
    config.motionCruiseVelocity = MAX_VELOCITY;
    config.motionAcceleration = MAX_ACCELERATION;
    config.peakCurrentLimit = PEAK_AMP;
    config.peakCurrentDuration = PEAK_AMP_DURATION;
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
    return config;
  }

  @Override
  public WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }
}
