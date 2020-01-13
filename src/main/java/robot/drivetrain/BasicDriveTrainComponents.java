package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_D;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_I;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_P;
import static robot.drivetrain.DriveTrainConstants.LEFT_MASTER_PORT;
import static robot.drivetrain.DriveTrainConstants.LEFT_SLAVE_PORT;
import static robot.drivetrain.DriveTrainConstants.PIGEON_NUMBER;
import static robot.drivetrain.DriveTrainConstants.RIGHT_MASTER_PORT;
import static robot.drivetrain.DriveTrainConstants.RIGHT_SLAVE_PORT;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;

public class BasicDriveTrainComponents implements DriveTrainComponents {
  private final WPI_TalonFX rightSlave;
  private final WPI_TalonFX rightMaster;
  private final WPI_TalonFX leftSlave;
  private final WPI_TalonFX leftMaster;
  private final DifferentialDrive differentialDrive;
  private final PigeonIMU gyro;
  private final DifferentialDriveOdometry odometry;

  public BasicDriveTrainComponents() {
    rightMaster = new WPI_TalonFX(RIGHT_MASTER_PORT);
    rightMaster.configFactoryDefault();
    rightMaster.configAllSettings(getConfiguration());

    rightSlave = new WPI_TalonFX(RIGHT_SLAVE_PORT);
    rightSlave.configFactoryDefault();
    rightSlave.follow(rightMaster);

    leftMaster = new WPI_TalonFX(LEFT_MASTER_PORT);
    leftMaster.configFactoryDefault();
    leftMaster.configAllSettings(getConfiguration());
    leftMaster.setInverted(true);

    leftSlave = new WPI_TalonFX(LEFT_SLAVE_PORT);
    leftSlave.follow(leftMaster);
    leftSlave.configFactoryDefault();
    leftSlave.setInverted(true);

    differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
    differentialDrive.setRightSideInverted(false);
    gyro = new PigeonIMU(PIGEON_NUMBER);

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getOdometryHeading()));
  }

  @Override
  public IMotorController getRightSlaveMotor() {
    return rightSlave;
  }

  @Override
  public WPI_TalonFX getRightMasterMotor() {
    return rightMaster;
  }

  @Override
  public IMotorController getLeftSlaveMotor() {
    return leftSlave;
  }

  @Override
  public WPI_TalonFX getLeftMasterMotor() {
    return leftMaster;
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

  public double getOdometryHeading() {
    final double[] yawPitchRoll = new double[3];
    gyro.getYawPitchRoll(yawPitchRoll);
    return Math.IEEEremainder(yawPitchRoll[0], 360);
  }

  private TalonFXConfiguration getConfiguration() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.slot0.kP = DRIVE_BY_DISTANCE_P;
    config.slot0.kI = DRIVE_BY_DISTANCE_I;
    config.slot0.kD = DRIVE_BY_DISTANCE_D;
    config.slot0.kF = DriveTrainConstants.PERCENTAGE_CLOSED_LOOP_OUTPUT *
        DriveTrainConstants.MAX_CLOSED_LOOP_OUTPUT / DriveTrainConstants.MAX_VELOCITY;
    return config;
  }
}
