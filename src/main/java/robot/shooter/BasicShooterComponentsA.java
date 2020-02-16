package robot.shooter;

import static robot.shooter.ShooterConstants.ShooterComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.DOUBLE_SOLENOID_FORWARD_PORT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.DOUBLE_SOLENOID_REVERSE_PORT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.MASTER_PORT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.OPEN_LOOP_RAMP;
import static robot.shooter.ShooterConstants.ShooterComponentsA.PEAK_AMP;
import static robot.shooter.ShooterConstants.ShooterComponentsA.PEAK_AMP_DURATION;
import static robot.shooter.ShooterConstants.ShooterComponentsA.SLAVE_PORT;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_D;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_F;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_I;
import static robot.shooter.ShooterConstants.ShooterComponentsA.VELOCITY_P;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class BasicShooterComponentsA implements ShooterComponents {

  private final WPI_TalonSRX masterMotor;
  private final WPI_VictorSPX slaveMotor;
  private final DoubleSolenoid doubleSolenoid;

  public BasicShooterComponentsA() {
    masterMotor = new WPI_TalonSRX(MASTER_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.setNeutralMode(NeutralMode.Coast);
    masterMotor.enableCurrentLimit(true);
    masterMotor.setInverted(true);
    masterMotor.setSensorPhase(true);

    slaveMotor = new WPI_VictorSPX(SLAVE_PORT);
    slaveMotor.configFactoryDefault();
    slaveMotor.setNeutralMode(NeutralMode.Coast);
    slaveMotor.follow(masterMotor);
    slaveMotor.setInverted(true);

    doubleSolenoid = new DoubleSolenoid(DOUBLE_SOLENOID_FORWARD_PORT, DOUBLE_SOLENOID_REVERSE_PORT);
  }

  private TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.slot0.kP = VELOCITY_P;
    config.slot0.kI = VELOCITY_I;
    config.slot0.kD = VELOCITY_D;
    config.slot0.kF = VELOCITY_F;
    config.peakCurrentLimit = PEAK_AMP;
    config.peakCurrentDuration = PEAK_AMP_DURATION;
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
    config.forwardLimitSwitchNormal = LimitSwitchNormal.Disabled;
    config.reverseLimitSwitchNormal = LimitSwitchNormal.Disabled;
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
    config.openloopRamp = OPEN_LOOP_RAMP;
    config.closedloopRamp = OPEN_LOOP_RAMP;
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
  public DoubleSolenoid getDoubleSolenoid() {
    return doubleSolenoid;
  }
}
