package robot.shooter;

import static robot.shooter.ShooterConstants.ShooterComponents.*;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BasicShooterComponents implements ShooterComponents {

  private final WPI_TalonSRX masterMotor;
  private final WPI_VictorSPX slaveMotor;

  public BasicShooterComponents() {
    masterMotor = new WPI_TalonSRX(MASTER_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.setNeutralMode(NeutralMode.Coast);
    masterMotor.enableCurrentLimit(true);
    masterMotor.setInverted(true);

    masterMotor.configOpenloopRamp(OPEN_LOOP_RAMP);
    masterMotor.configClosedloopRamp(CLOSE_LOOP_RAMP);
    masterMotor.setSensorPhase(true);

    slaveMotor = new WPI_VictorSPX(SLAVE_PORT);
    slaveMotor.configFactoryDefault();
    slaveMotor.setNeutralMode(NeutralMode.Coast);
    slaveMotor.follow(masterMotor);
    slaveMotor.setInverted(true);

    slaveMotor.configOpenloopRamp(OPEN_LOOP_RAMP);
    slaveMotor.configClosedloopRamp(CLOSE_LOOP_RAMP);

    masterMotor.selectProfileSlot(VELOCITY_PID_SLOT, PRIMARY_PID);
  }

  private TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.slot0.kP = VELOCITY_P;
    config.slot0.kI = VELOCITY_I;
    config.slot0.kD = VELOCITY_D;
    config.slot0.kF = VELOCITY_F;
    config.peakCurrentLimit = PICK_AMP;
    config.peakCurrentDuration = PICK_AMP_DURATION;
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
    config.forwardLimitSwitchNormal = LimitSwitchNormal.Disabled;
    config.reverseLimitSwitchNormal = LimitSwitchNormal.Disabled;
    config.auxiliaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
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
