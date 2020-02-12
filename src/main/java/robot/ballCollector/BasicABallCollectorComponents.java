package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.CONTINUOUS_CURRENT_LIMIT;
import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.DOUBLE_SOLENOID_FORWARD_PORT;
import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.DOUBLE_SOLENOID_REVERSE_PORT;
import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.MASTER_MOTOR_PORT;
import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.PEAK_AMP;
import static robot.ballCollector.BallCollectorConstants.BallCollectorComponentsA.PEAK_AMP_DURATION;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class BasicABallCollectorComponents implements BallCollectorComponents {

  private final WPI_TalonSRX masterMotor;
  private final DoubleSolenoid doubleSolenoid;

  public BasicABallCollectorComponents() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getConfiguration());
    masterMotor.enableCurrentLimit(true);
    masterMotor.setNeutralMode(NeutralMode.Brake);
    masterMotor.setInverted(true);

    doubleSolenoid = new DoubleSolenoid(DOUBLE_SOLENOID_FORWARD_PORT, DOUBLE_SOLENOID_REVERSE_PORT);
  }

  public TalonSRXConfiguration getConfiguration(){
    final TalonSRXConfiguration config = new TalonSRXConfiguration();
    masterMotor.configPeakCurrentLimit(PEAK_AMP);
    masterMotor.configPeakCurrentDuration(PEAK_AMP_DURATION);
    masterMotor.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
    return config;
  }

  @Override
  public WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }

  @Override
  public DoubleSolenoid getDoubleSolenoid() {
    return doubleSolenoid;
  }
}
