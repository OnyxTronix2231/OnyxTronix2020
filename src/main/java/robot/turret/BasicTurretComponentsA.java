package robot.turret;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.turret.TurretConstants.TurretComponentsA.*;

public class BasicTurretComponentsA implements TurretComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicTurretComponentsA() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.enableCurrentLimit(true);
    masterMotor.setNeutralMode(NeutralMode.Brake);
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
    return config;
  }

  @Override
  public WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }
}
