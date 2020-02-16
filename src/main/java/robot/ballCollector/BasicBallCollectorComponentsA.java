package robot.ballCollector;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.*;

public class BasicBallCollectorComponentsA implements BallCollectorComponents {

  private final WPI_TalonSRX masterMotor;
  private final WPI_VictorSPX slaveMotor;
  private final DoubleSolenoid doubleSolenoid;

  public BasicBallCollectorComponentsA() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.enableCurrentLimit(true);
    masterMotor.setNeutralMode(NeutralMode.Brake);
    masterMotor.setInverted(true);

    slaveMotor = new WPI_VictorSPX(SLAVE_MOTOR_PORT);
    slaveMotor.configFactoryDefault();
    slaveMotor.setNeutralMode(NeutralMode.Brake);
    slaveMotor.setInverted(true);
    slaveMotor.follow(masterMotor);

    doubleSolenoid = new DoubleSolenoid(DOUBLE_SOLENOID_FORWARD_PORT, DOUBLE_SOLENOID_REVERSE_PORT);
  }

  public TalonSRXConfiguration getConfiguration() {
    final TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = PEAK_AMP;
    config.peakCurrentDuration = PEAK_AMP_DURATION;
    config.continuousCurrentLimit = CONTINUOUS_CURRENT_LIMIT;
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
