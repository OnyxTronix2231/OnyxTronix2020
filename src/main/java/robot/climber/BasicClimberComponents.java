package robot.climber;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class BasicClimberComponents implements ClimberComponents {

  private final WPI_TalonSRX masterMotor;
  private final WPI_VictorSPX slaveMotor;
  private final DoubleSolenoid rightDoubleSolenoid;
  private final DoubleSolenoid leftDoubleSolenoid;

  public BasicClimberComponents() {
    masterMotor = new WPI_TalonSRX(ClimberConstants.MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();

    slaveMotor = new WPI_VictorSPX(ClimberConstants.SLAVE_MOTOR_PORT);
    slaveMotor.configFactoryDefault();
    slaveMotor.follow(masterMotor);

    leftDoubleSolenoid = new DoubleSolenoid(ClimberConstants.DOUBLE_LEFT_SOLENOID_FORWARD_PORT,
        ClimberConstants.DOUBLE_LEFT_SOLENOID_REVERSE_PORT);
    rightDoubleSolenoid = new DoubleSolenoid(ClimberConstants.DOUBLE_RIGHT_SOLENOID_FORWARD_PORT,
        ClimberConstants.DOUBLE_RIGHT_SOLENOID_REVERSE_PORT);
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
  public DoubleSolenoid getLeftDoubleSolenoid() {
    return leftDoubleSolenoid;
  }

  @Override
  public DoubleSolenoid getRightDoubleSolenoid() {
    return rightDoubleSolenoid;
  }
}
