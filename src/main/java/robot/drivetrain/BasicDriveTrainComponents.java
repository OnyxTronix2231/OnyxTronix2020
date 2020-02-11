package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.CLOSED_LOOP_RAMP;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.CURRENT_LIMIT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.DRIVE_BY_DISTANCE_D;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.DRIVE_BY_DISTANCE_I;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.DRIVE_BY_DISTANCE_P;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.LEFT_MASTER_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.LEFT_SLAVE_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.MAX_CLOSED_LOOP_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.MAX_VELOCITY;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.OPEN_LOOP_RAMP;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.PERCENTAGE_CLOSED_LOOP_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.RIGHT_MASTER_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.RIGHT_SLAVE_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.TRIGGER_THRESHOLD_CURRENT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.TRIGGER_THRESHOLD_TIME;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.VELOCITY_CONTROLLER_D;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.VELOCITY_CONTROLLER_I;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponents.VELOCITY_CONTROLLER_P;
import static robot.drivetrain.DriveTrainConstants.VELOCITY_CONTROLLER_PID_SLOT;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class BasicDriveTrainComponents implements DriveTrainComponents {

  private final WPI_TalonFX rightMaster;
  private final WPI_TalonFX rightSlave;
  private final WPI_TalonFX leftMaster;
  private final WPI_TalonFX leftSlave;
  private final DifferentialDrive differentialDrive;

  public BasicDriveTrainComponents() {
    rightMaster = new WPI_TalonFX(RIGHT_MASTER_PORT);
    rightMaster.configFactoryDefault();
    rightMaster.setInverted(true);
    rightMaster.configAllSettings(getFalconConfiguration());
    rightMaster.configSupplyCurrentLimit(getCurrentConfiguration());
    rightMaster.setNeutralMode(NeutralMode.Brake);

    rightSlave = new WPI_TalonFX(RIGHT_SLAVE_PORT);
    rightSlave.configFactoryDefault();
    rightSlave.setInverted(true);
    rightSlave.configSupplyCurrentLimit(getCurrentConfiguration());
    rightSlave.setNeutralMode(NeutralMode.Brake);
    rightSlave.follow(rightMaster);

    leftMaster = new WPI_TalonFX(LEFT_MASTER_PORT);
    leftMaster.configFactoryDefault();
    leftMaster.configAllSettings(getFalconConfiguration());
    leftMaster.configSupplyCurrentLimit(getCurrentConfiguration());
    leftMaster.setNeutralMode(NeutralMode.Brake);

    leftSlave = new WPI_TalonFX(LEFT_SLAVE_PORT);
    leftSlave.configFactoryDefault();
    leftSlave.configSupplyCurrentLimit(getCurrentConfiguration());
    leftSlave.setNeutralMode(NeutralMode.Brake);
    leftSlave.follow(leftMaster);

    VelocityController leftVelocityController = new TalonFXVelocityController(MAX_VELOCITY, VELOCITY_CONTROLLER_PID_SLOT, leftMaster);
    VelocityController rightVelocityController = new TalonFXVelocityController(MAX_VELOCITY, VELOCITY_CONTROLLER_PID_SLOT, rightMaster);

    differentialDrive = new DifferentialDrive(leftVelocityController, rightVelocityController);
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
    config.slot0.kP = DRIVE_BY_DISTANCE_P;
    config.slot0.kI = DRIVE_BY_DISTANCE_I;
    config.slot0.kD = DRIVE_BY_DISTANCE_D;
    config.slot0.kF = PERCENTAGE_CLOSED_LOOP_OUTPUT * MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    config.slot2.kP = VELOCITY_CONTROLLER_P;
    config.slot2.kI = VELOCITY_CONTROLLER_I;
    config.slot2.kD = VELOCITY_CONTROLLER_D;
    config.slot2.kF = PERCENTAGE_CLOSED_LOOP_OUTPUT * MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    config.openloopRamp = OPEN_LOOP_RAMP;
    config.closedloopRamp = CLOSED_LOOP_RAMP;
    return config;
  }

  private SupplyCurrentLimitConfiguration getCurrentConfiguration() {
    return new SupplyCurrentLimitConfiguration(true, CURRENT_LIMIT, TRIGGER_THRESHOLD_CURRENT, TRIGGER_THRESHOLD_TIME);
  }
}
