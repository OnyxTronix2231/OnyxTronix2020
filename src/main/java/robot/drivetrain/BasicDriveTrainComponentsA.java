package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CLOSED_LOOP_RAMP;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.CURRENT_LIMIT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.DRIVE_BY_DISTANCE_D;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.DRIVE_BY_DISTANCE_I;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.DRIVE_BY_DISTANCE_P;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.LEFT_MASTER_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.LEFT_SLAVE_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_ACCELERATION;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_CLOSED_LOOP_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_OUTPUT_FORWARD;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_OUTPUT_REVERSE;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.MAX_VELOCITY;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.OPEN_LOOP_RAMP;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.PERCENTAGE_CLOSED_LOOP_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.PIGEON_CONNECTED_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.RIGHT_MASTER_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.RIGHT_SLAVE_PORT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TRAJECTORY_D;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TRAJECTORY_I;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TRAJECTORY_P;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TRIGGER_THRESHOLD_CURRENT;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TRIGGER_THRESHOLD_TIME;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.VELOCITY_CONTROLLER_D;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.VELOCITY_CONTROLLER_I;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.VELOCITY_CONTROLLER_P;
import static robot.drivetrain.DriveTrainConstants.TrajectoryParamsA.MAX_ACCELERATION_METERS_PER_SECOND_SQUARED;
import static robot.drivetrain.DriveTrainConstants.TrajectoryParamsA.MAX_SPEED_METERS_PER_SECOND;
import static robot.drivetrain.DriveTrainConstants.TrajectoryParamsA.MAX_VOLTAGE;
import static robot.drivetrain.DriveTrainConstants.TrajectoryParamsA.TRACKWIDTH_METERS;
import static robot.drivetrain.DriveTrainConstants.TrajectoryParamsA.VOLTS;
import static robot.drivetrain.DriveTrainConstants.TrajectoryParamsA.VOLT_SECONDS_PER_METER;
import static robot.drivetrain.DriveTrainConstants.TrajectoryParamsA.VOLT_SECONDS_SQUARED_PER_METER;
import static robot.drivetrain.DriveTrainConstants.VELOCITY_CONTROLLER_PID_SLOT;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import controllers.VelocityController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

public class BasicDriveTrainComponentsA implements DriveTrainComponents {

  private final WPI_TalonFX rightMaster;
  private final WPI_TalonFX rightSlave;
  private final WPI_TalonFX leftMaster;
  private final WPI_TalonFX leftSlave;
  private final DifferentialDrive differentialDrive;
  private final PigeonIMU gyro;
  private final DifferentialDriveOdometry odometry;
  private final SimpleMotorFeedforward motorFeedforward;
  private final DifferentialDriveKinematics driveKinematics;
  private final DifferentialDriveVoltageConstraint autonomousVoltage;
  private final TrajectoryConfig trajectoryConfig;

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

    final VelocityController leftVelocityController = new VelocityController(leftMaster, MAX_VELOCITY,
        VELOCITY_CONTROLLER_PID_SLOT);
    final VelocityController rightVelocityController = new VelocityController(rightMaster, MAX_VELOCITY,
        VELOCITY_CONTROLLER_PID_SLOT);

    differentialDrive = new DifferentialDrive(leftVelocityController, rightVelocityController);
    differentialDrive.setRightSideInverted(false);
    differentialDrive.setSafetyEnabled(false);

    gyro = new PigeonIMU(new WPI_TalonSRX(PIGEON_CONNECTED_PORT));
    gyro.setYaw(0);

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
    odometry.resetPosition(new Pose2d(), new Rotation2d());

    motorFeedforward = new SimpleMotorFeedforward(VOLTS, VOLT_SECONDS_PER_METER, VOLT_SECONDS_SQUARED_PER_METER);

    driveKinematics = new DifferentialDriveKinematics(TRACKWIDTH_METERS);

    autonomousVoltage = new DifferentialDriveVoltageConstraint(motorFeedforward, driveKinematics, MAX_VOLTAGE);

    trajectoryConfig = new TrajectoryConfig(MAX_SPEED_METERS_PER_SECOND, MAX_ACCELERATION_METERS_PER_SECOND_SQUARED)
        .setKinematics(driveKinematics).addConstraint(autonomousVoltage);
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
  public PigeonIMU getGyro() {
    return gyro;
  }

  @Override
  public DifferentialDriveOdometry getOdometry() {
    return odometry;
  }

  @Override
  public SimpleMotorFeedforward getMotorFeedForward() {
    return motorFeedforward;
  }

  @Override
  public DifferentialDriveKinematics getDriveKinematics() {
    return driveKinematics;
  }

  @Override
  public DifferentialDriveVoltageConstraint getAutonomousVoltage() {
    return autonomousVoltage;
  }

  @Override
  public TrajectoryConfig getTrajectoryConfig() {
    return trajectoryConfig;
  }

  @Override
  public double getPigeonYaw() {
    double[] yawPitchRaw = new double[3];
    gyro.getYawPitchRoll(yawPitchRaw);
    return yawPitchRaw[0];
  }

  private TalonFXConfiguration getFalconConfiguration() {
    final TalonFXConfiguration config = new TalonFXConfiguration();
    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    config.slot0.kP = DRIVE_BY_DISTANCE_P;
    config.slot0.kI = DRIVE_BY_DISTANCE_I;
    config.slot0.kD = DRIVE_BY_DISTANCE_D;
    config.slot0.kF = PERCENTAGE_CLOSED_LOOP_OUTPUT * MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    config.slot1.kP = TRAJECTORY_P;
    config.slot1.kI = TRAJECTORY_I;
    config.slot1.kD = TRAJECTORY_D;
    config.slot1.kF = PERCENTAGE_CLOSED_LOOP_OUTPUT * MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    config.slot2.kP = VELOCITY_CONTROLLER_P;
    config.slot2.kI = VELOCITY_CONTROLLER_I;
    config.slot2.kD = VELOCITY_CONTROLLER_D;
    config.slot2.kF = PERCENTAGE_CLOSED_LOOP_OUTPUT * MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    config.motionCruiseVelocity = MAX_VELOCITY;
    config.motionAcceleration = MAX_ACCELERATION;
    config.peakOutputForward = MAX_OUTPUT_FORWARD;
    config.peakOutputReverse = MAX_OUTPUT_REVERSE;
    config.supplyCurrLimit.currentLimit = CURRENT_LIMIT;
    config.supplyCurrLimit.triggerThresholdCurrent = TRIGGER_THRESHOLD_CURRENT;
    config.supplyCurrLimit.triggerThresholdTime = TRIGGER_THRESHOLD_TIME;
    config.supplyCurrLimit.enable = true;
    config.openloopRamp = OPEN_LOOP_RAMP;
    config.closedloopRamp = CLOSED_LOOP_RAMP;
    return config;
  }
}
