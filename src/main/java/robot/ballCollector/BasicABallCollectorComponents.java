package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.RobotAComponents.CONTINUOUS_CURRENT_LIMIT;
import static robot.ballCollector.BallCollectorConstants.RobotAComponents.DOUBLE_SOLENOID_FORWARD_PORT;
import static robot.ballCollector.BallCollectorConstants.RobotAComponents.DOUBLE_SOLENOID_REVERSE_PORT;
import static robot.ballCollector.BallCollectorConstants.RobotAComponents.MASTER_MOTOR_PORT;
import static robot.ballCollector.BallCollectorConstants.RobotAComponents.PICK_AMP;
import static robot.ballCollector.BallCollectorConstants.RobotAComponents.PICK_AMP_DURATION;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class BasicABallCollectorComponents implements BallCollectorComponents {

  private final WPI_TalonSRX masterMotor;
  private final DoubleSolenoid doubleSolenoid;

  public BasicABallCollectorComponents() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configPeakCurrentLimit(PICK_AMP);
    masterMotor.configPeakCurrentDuration(PICK_AMP_DURATION);
    masterMotor.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
    masterMotor.enableCurrentLimit(true);
    masterMotor.setNeutralMode(NeutralMode.Brake);
    masterMotor.setInverted(true);

    doubleSolenoid = new DoubleSolenoid(DOUBLE_SOLENOID_FORWARD_PORT, DOUBLE_SOLENOID_REVERSE_PORT);
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
