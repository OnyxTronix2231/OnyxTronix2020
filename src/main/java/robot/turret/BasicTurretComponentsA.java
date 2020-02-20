package robot.turret;

import static robot.turret.TurretConstants.TurretComponentsA.ABSOLUTE_ENCODER_OFFSET;
import static robot.turret.TurretConstants.TurretComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.turret.TurretConstants.TurretComponentsA.FORWARD_SOFT_LIMIT_ENABLE;
import static robot.turret.TurretConstants.TurretComponentsA.FORWARD_SOFT_LIMIT_THRESHOLD;
import static robot.turret.TurretConstants.TurretComponentsA.MASTER_MOTOR_PORT;
import static robot.turret.TurretConstants.TurretComponentsA.MAX_ACCELERATION;
import static robot.turret.TurretConstants.TurretComponentsA.MAX_VELOCITY;
import static robot.turret.TurretConstants.TurretComponentsA.PEAK_AMP;
import static robot.turret.TurretConstants.TurretComponentsA.PEAK_AMP_DURATION;
import static robot.turret.TurretConstants.TurretComponentsA.REVERSE_SOFT_LIMIT_ENABLE;
import static robot.turret.TurretConstants.TurretComponentsA.REVERSE_SOFT_LIMIT_THRESHOLD;
import static robot.turret.TurretConstants.TurretComponentsA.VELOCITY_D;
import static robot.turret.TurretConstants.TurretComponentsA.VELOCITY_F;
import static robot.turret.TurretConstants.TurretComponentsA.VELOCITY_I;
import static robot.turret.TurretConstants.TurretComponentsA.VELOCITY_P;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicTurretComponentsA implements TurretComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicTurretComponentsA() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.enableCurrentLimit(true);
    masterMotor.setNeutralMode(NeutralMode.Brake);
    masterMotor.setInverted(true);
    masterMotor.setSensorPhase(true);
  }

  private TalonSRXConfiguration getConfiguration() {
    final TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.Analog;
    config.slot0.kP = VELOCITY_P;
    config.slot0.kI = VELOCITY_I;
    config.slot0.kD = VELOCITY_D;
    config.slot0.kF = VELOCITY_F;
    config.motionCruiseVelocity = MAX_VELOCITY;
    config.motionAcceleration = MAX_ACCELERATION;
    config.peakCurrentLimit = PEAK_AMP;
    config.peakCurrentDuration = PEAK_AMP_DURATION;
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
    config.forwardSoftLimitThreshold = FORWARD_SOFT_LIMIT_THRESHOLD;
    config.reverseSoftLimitThreshold = REVERSE_SOFT_LIMIT_THRESHOLD;
    config.forwardSoftLimitEnable = FORWARD_SOFT_LIMIT_ENABLE;
    config.reverseSoftLimitEnable = REVERSE_SOFT_LIMIT_ENABLE;
    return config;
  }

  @Override
  public WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }

  @Override
  public double getAbsoluteEncoderOffset() {
    return ABSOLUTE_ENCODER_OFFSET;
  }

}
