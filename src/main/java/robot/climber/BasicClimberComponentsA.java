package robot.climber;

import static robot.climber.ClimberConstants.ClimberComponentsA.CURRENT_LIMIT;
import static robot.climber.ClimberConstants.ClimberComponentsA.DOUBLE_SOLENOID_PORT_FORWARD;
import static robot.climber.ClimberConstants.ClimberComponentsA.DOUBLE_SOLENOID_PORT_REVERSE;
import static robot.climber.ClimberConstants.ClimberComponentsA.MASTER_MOTOR_PORT;
import static robot.climber.ClimberConstants.ClimberComponentsA.SLAVE_MOTOR_PORT;
import static robot.climber.ClimberConstants.ClimberComponentsA.TRIGGER_THRESHOLD_CURRENT;
import static robot.climber.ClimberConstants.ClimberComponentsA.TRIGGER_THRESHOLD_TIME;
import static robot.climber.ClimberConstants.ClimberComponentsA.VELOCITY_KD;
import static robot.climber.ClimberConstants.ClimberComponentsA.VELOCITY_KF;
import static robot.climber.ClimberConstants.ClimberComponentsA.VELOCITY_KI;
import static robot.climber.ClimberConstants.ClimberComponentsA.VELOCITY_KP;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class BasicClimberComponentsA implements ClimberComponents {

  private final WPI_TalonFX masterMotor;
  private final WPI_TalonFX slaveMotor;
  private final DoubleSolenoid doubleSolenoid;

  public BasicClimberComponentsA() {
    masterMotor = new WPI_TalonFX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configAllSettings(getFalconConfiguration());
    masterMotor.setNeutralMode(NeutralMode.Brake);

    slaveMotor = new WPI_TalonFX(SLAVE_MOTOR_PORT);
    slaveMotor.configFactoryDefault();
    slaveMotor.configAllSettings(getFalconConfiguration());
    slaveMotor.setNeutralMode(NeutralMode.Brake);
    slaveMotor.follow(masterMotor);

    doubleSolenoid = new DoubleSolenoid(DOUBLE_SOLENOID_PORT_FORWARD,
        DOUBLE_SOLENOID_PORT_REVERSE);
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
  public DoubleSolenoid getDoubleSolenoid() {
    return doubleSolenoid;
  }

  private TalonFXConfiguration getFalconConfiguration() {
    final TalonFXConfiguration config = new TalonFXConfiguration();
    config.slot0.kP = VELOCITY_KP;
    config.slot0.kI = VELOCITY_KI;
    config.slot0.kD = VELOCITY_KD;
    config.slot0.kF = VELOCITY_KF;
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    config.supplyCurrLimit.currentLimit = CURRENT_LIMIT;
    config.supplyCurrLimit.triggerThresholdCurrent = TRIGGER_THRESHOLD_CURRENT;
    config.supplyCurrLimit.triggerThresholdTime = TRIGGER_THRESHOLD_TIME;
    config.supplyCurrLimit.enable = true;
    return config;
  }
}

