package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.CURRENT_LIMIT;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_D;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_I;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_P;
import static robot.drivetrain.DriveTrainConstants.LEFT_MASTER_PORT;
import static robot.drivetrain.DriveTrainConstants.LEFT_SLAVE_PORT;
import static robot.drivetrain.DriveTrainConstants.MAX_CLOSED_LOOP_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.MAX_VELOCITY;
import static robot.drivetrain.DriveTrainConstants.PERCENTAGE_CLOSED_LOOP_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.RIGHT_MASTER_PORT;
import static robot.drivetrain.DriveTrainConstants.RIGHT_SLAVE_PORT;
import static robot.drivetrain.DriveTrainConstants.TRIGGER_THRESHOLD_CURRENT;
import static robot.drivetrain.DriveTrainConstants.TRIGGER_THRESHOLD_TIME;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;

public class BasicDriveTrainComponents implements DriveTrainComponents {

  private final WPI_TalonFX rightMaster;
  private final WPI_TalonFX rightSlave;
  private final WPI_TalonFX leftMaster;
  private final WPI_TalonFX leftSlave;
  private final DifferentialDrive differentialDrive;
  private final AHRS gyro;
  private final DifferentialDriveOdometry odometry;

  public BasicDriveTrainComponents() {
    rightMaster = new WPI_TalonFX(RIGHT_MASTER_PORT);
    rightMaster.configFactoryDefault();
    rightMaster.configAllSettings(getFalconConfiguration());
    rightMaster.configSupplyCurrentLimit(getCurrentConfiguration());
    rightMaster.setNeutralMode(NeutralMode.Brake);

    rightSlave = new WPI_TalonFX(RIGHT_SLAVE_PORT);
    rightSlave.configFactoryDefault();
    rightSlave.configSupplyCurrentLimit(getCurrentConfiguration());
    rightSlave.setNeutralMode(NeutralMode.Brake);
    rightSlave.follow(rightMaster);

    leftMaster = new WPI_TalonFX(LEFT_MASTER_PORT);
    leftMaster.configFactoryDefault();
    leftMaster.configAllSettings(getFalconConfiguration());
    leftMaster.setInverted(true);
    leftMaster.configSupplyCurrentLimit(getCurrentConfiguration());
    leftMaster.setNeutralMode(NeutralMode.Brake);

    leftSlave = new WPI_TalonFX(LEFT_SLAVE_PORT);
    leftSlave.configFactoryDefault();
    leftSlave.setInverted(true);
    leftSlave.configSupplyCurrentLimit(getCurrentConfiguration());
    leftSlave.setNeutralMode(NeutralMode.Brake);
    leftSlave.follow(leftMaster);

    differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
    differentialDrive.setRightSideInverted(false);
    differentialDrive.setSafetyEnabled(false);
    gyro = new AHRS();

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(Math.IEEEremainder(gyro.getYaw(), 360)));
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

  @Override
  public AHRS getGyro() {
    return gyro;
  }

  @Override
  public DifferentialDriveOdometry getOdometry() {
    return odometry;
  }

  private TalonFXConfiguration getFalconConfiguration() {
    final TalonFXConfiguration config = new TalonFXConfiguration();
    config.slot0.kP = DRIVE_BY_DISTANCE_P;
    config.slot0.kI = DRIVE_BY_DISTANCE_I;
    config.slot0.kD = DRIVE_BY_DISTANCE_D;
    config.slot0.kF = PERCENTAGE_CLOSED_LOOP_OUTPUT * MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    config.motionCruiseVelocity = (int) MAX_VELOCITY;
    return config;
  }

  private SupplyCurrentLimitConfiguration getCurrentConfiguration() {
    return new SupplyCurrentLimitConfiguration(true, CURRENT_LIMIT, TRIGGER_THRESHOLD_CURRENT, TRIGGER_THRESHOLD_TIME);
  }
}
