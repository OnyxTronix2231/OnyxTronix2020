package robot.climber;

import static robot.climber.ClimberConstants.ClimberComponentsA.CURRENT_LIMIT;
import static robot.climber.ClimberConstants.ClimberComponentsA.LEFT_DOUBLE_SOLENOID_FORWARD_PORT;
import static robot.climber.ClimberConstants.ClimberComponentsA.LEFT_DOUBLE_SOLENOID_REVERSE_PORT;
import static robot.climber.ClimberConstants.ClimberComponentsA.MASTER_MOTOR_PORT;
import static robot.climber.ClimberConstants.ClimberComponentsA.RIGHT_DOUBLE_SOLENOID_FORWARD_PORT;
import static robot.climber.ClimberConstants.ClimberComponentsA.RIGHT_DOUBLE_SOLENOID_REVERSE_PORT;
import static robot.climber.ClimberConstants.ClimberComponentsA.SLAVE_MOTOR_PORT;
import static robot.climber.ClimberConstants.ClimberComponentsA.TRIGGER_THRESHOLD_CURRENT;
import static robot.climber.ClimberConstants.ClimberComponentsA.TRIGGER_THRESHOLD_TIME;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class BasicClimberComponentsA implements ClimberComponents {

  private final WPI_TalonFX masterMotor;
  private final WPI_TalonFX slaveMotor;
  private final DoubleSolenoid rightDoubleSolenoid;
  private final DoubleSolenoid leftDoubleSolenoid;

  public BasicClimberComponentsA() {
    masterMotor = new WPI_TalonFX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getFalconConfiguration());
    masterMotor.configSupplyCurrentLimit(getCurrentConfiguration());
    masterMotor.setNeutralMode(NeutralMode.Brake);

    slaveMotor = new WPI_TalonFX(SLAVE_MOTOR_PORT);
    slaveMotor.configFactoryDefault();
    masterMotor.configAllSettings(getFalconConfiguration());
    masterMotor.configSupplyCurrentLimit(getCurrentConfiguration());
    masterMotor.setNeutralMode(NeutralMode.Brake);
    slaveMotor.follow(masterMotor);

    leftDoubleSolenoid = new DoubleSolenoid(LEFT_DOUBLE_SOLENOID_FORWARD_PORT,
        LEFT_DOUBLE_SOLENOID_REVERSE_PORT);
    rightDoubleSolenoid = new DoubleSolenoid(RIGHT_DOUBLE_SOLENOID_FORWARD_PORT,
        RIGHT_DOUBLE_SOLENOID_REVERSE_PORT);
  }

  @Override
  public WPI_TalonFX getMasterMotor() {
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
  private TalonFXConfiguration getFalconConfiguration() {
    final TalonFXConfiguration config = new TalonFXConfiguration();
    return config;
  }

  private SupplyCurrentLimitConfiguration getCurrentConfiguration() {
    return new SupplyCurrentLimitConfiguration(true, CURRENT_LIMIT, TRIGGER_THRESHOLD_CURRENT,
        TRIGGER_THRESHOLD_TIME);
  }
}
