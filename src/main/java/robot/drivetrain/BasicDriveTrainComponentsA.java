package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CLOSED_LOOP_RAMP;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CURRENT_LIMIT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.DRIVE_BY_DISTANCE_D;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.DRIVE_BY_DISTANCE_I;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.DRIVE_BY_DISTANCE_P;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.LEFT_MASTER_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.LEFT_SLAVE_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_CLOSED_LOOP_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_VELOCITY;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.OPEN_LOOP_RAMP;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.PERCENTAGE_CLOSED_LOOP_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.RIGHT_MASTER_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.RIGHT_SLAVE_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TRIGGER_THRESHOLD_CURRENT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TRIGGER_THRESHOLD_TIME;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.VELOCITY_CONTROLLER_D;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.VELOCITY_CONTROLLER_I;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.VELOCITY_CONTROLLER_P;
import static robot.drivetrain.DriveTrainConstants.VELOCITY_CONTROLLER_PID_SLOT;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import controllers.VelocityController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class BasicDriveTrainComponentsA implements DriveTrainComponents {

  private final WPI_TalonFX rightMaster;
  private final WPI_TalonFX rightSlave;
  private final WPI_TalonFX leftMaster;
  private final WPI_TalonFX leftSlave;
  private final DifferentialDrive differentialDrive;

  public BasicDriveTrainComponentsA() {
    rightMaster = new WPI_TalonFX(RIGHT_MASTER_PORT);
    rightMaster.configFactoryDefault();
    rightMaster.configAllSettings(getFalconConfiguration());
    rightMaster.setInverted(true);
    rightMaster.setNeutralMode(NeutralMode.Brake);

    rightSlave = new WPI_TalonFX(RIGHT_SLAVE_PORT);
    rightSlave.configFactoryDefault();
    rightSlave.configAllSettings(getFalconConfiguration());
    rightSlave.setInverted(true);
    rightSlave.setNeutralMode(NeutralMode.Brake);
    rightSlave.follow(rightMaster);

    leftMaster = new WPI_TalonFX(LEFT_MASTER_PORT);
    leftMaster.configFactoryDefault();
    leftMaster.configAllSettings(getFalconConfiguration());
    leftMaster.setNeutralMode(NeutralMode.Brake);

    leftSlave = new WPI_TalonFX(LEFT_SLAVE_PORT);
    leftSlave.configFactoryDefault();
    leftSlave.configAllSettings(getFalconConfiguration());
    leftSlave.setNeutralMode(NeutralMode.Brake);
    leftSlave.follow(leftMaster);

    final VelocityController leftVelocityController = new VelocityController(MAX_VELOCITY, VELOCITY_CONTROLLER_PID_SLOT,
        leftMaster);
    final VelocityController rightVelocityController = new VelocityController(MAX_VELOCITY, VELOCITY_CONTROLLER_PID_SLOT,
        rightMaster);

//    differentialDrive = new DifferentialDrive(leftVelocityController, rightVelocityController);
    differentialDrive = new DifferentialDrive(leftMaster, rightMaster); //TODO: Calibrate Velocity PID and remove this
    differentialDrive.setRightSideInverted(false);
    differentialDrive.setSafetyEnabled(false);
  }

  @Override
  public WPI_TalonFX getRightMasterMotor() {
    return rightMaster;
  }

  @Override
  public IMotorController getRightSlaveMotor() {
    return rightSlave;
  }

  @Override
  public WPI_TalonFX getLeftMasterMotor() {
    return leftMaster;
  }

  @Override
  public IMotorController getLeftSlaveMotor() {
    return leftSlave;
  }

  @Override
  public DifferentialDrive getDifferentialDrive() {
    return differentialDrive;
  }

  private TalonFXConfiguration getFalconConfiguration() {
    final TalonFXConfiguration config = new TalonFXConfiguration();
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    config.slot0.kP = DRIVE_BY_DISTANCE_P;
    config.slot0.kI = DRIVE_BY_DISTANCE_I;
    config.slot0.kD = DRIVE_BY_DISTANCE_D;
    config.slot0.kF = PERCENTAGE_CLOSED_LOOP_OUTPUT * MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    config.slot2.kP = VELOCITY_CONTROLLER_P;
    config.slot2.kI = VELOCITY_CONTROLLER_I;
    config.slot2.kD = VELOCITY_CONTROLLER_D;
    config.slot2.kF = PERCENTAGE_CLOSED_LOOP_OUTPUT * MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    config.supplyCurrLimit.currentLimit = CURRENT_LIMIT;
    config.supplyCurrLimit.triggerThresholdCurrent = TRIGGER_THRESHOLD_CURRENT;
    config.supplyCurrLimit.triggerThresholdTime = TRIGGER_THRESHOLD_TIME;
    config.supplyCurrLimit.enable = true;
    config.openloopRamp = OPEN_LOOP_RAMP;
    config.closedloopRamp = CLOSED_LOOP_RAMP;
    return config;
  }
}
