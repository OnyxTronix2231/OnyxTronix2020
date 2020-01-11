package robot.climber;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class BasicClimberComponents implements ClimberComponents {

  private final WPI_TalonSRX masterMotor;
  private final WPI_VictorSPX slaveMotor;
  private final DoubleSolenoid doubleSolenoid;

  public BasicClimberComponents() {
    masterMotor = new WPI_TalonSRX(ClimberConstants.MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();

    slaveMotor = new WPI_VictorSPX(ClimberConstants.SLAVE_MOTOR_PORT);
    slaveMotor.configFactoryDefault();
    slaveMotor.follow(masterMotor);

    doubleSolenoid = new DoubleSolenoid(ClimberConstants.FORWARD_PORT,ClimberConstants.REVERSE_PORT);
  }

  @Override
  public final WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }

  @Override
  public final IMotorController getSlaveMotor() {
    return slaveMotor;
  }

  @Override
  public final DoubleSolenoid getDoubleSolenoid() {
    return doubleSolenoid;
  }
}
