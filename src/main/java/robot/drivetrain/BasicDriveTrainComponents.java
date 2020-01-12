package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_D;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_I;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_P;
import static robot.drivetrain.DriveTrainConstants.MASTER_LEFT_PORT;
import static robot.drivetrain.DriveTrainConstants.MASTER_RIGHT_PORT;
import static robot.drivetrain.DriveTrainConstants.MAX_CLOSED_LOOP_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.MAX_VELOCITY;
import static robot.drivetrain.DriveTrainConstants.PERCENTAGE_CLOSED_LOOP_OUTPUT;
import static robot.drivetrain.DriveTrainConstants.SLAVE_LEFT_PORT;
import static robot.drivetrain.DriveTrainConstants.SLAVE_RIGHT_PORT;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;

public class BasicDriveTrainComponents implements DriveTrainComponents {
  private final WPI_VictorSPX rightSlave;
  private final WPI_TalonSRX rightMaster;
  private final WPI_VictorSPX leftSlave;
  private final WPI_TalonSRX leftMaster;
  private final DifferentialDrive differentialDrive;
  private final PigeonIMU gyro;
  private final DifferentialDriveOdometry odometry;

<<<<<<<
  public BasicDriveTrainComponents() {
    rightMaster = new WPI_TalonSRX(2);
    rightMaster.configFactoryDefault();
    rightMaster.configAllSettings(getConfiguration());
=======
  private final WPI_TalonFX rightSlave;
  private final WPI_TalonFX rightMaster;
  private final WPI_TalonFX leftSlave;
  private final WPI_TalonFX leftMaster;
  private final DifferentialDrive differentialDrive;
>>>>>>>

  public BasicDriveTrainComponents() {
    rightMaster = new WPI_TalonFX(MASTER_RIGHT_PORT);
    rightMaster.configFactoryDefault();

    rightSlave = new WPI_TalonFX(SLAVE_RIGHT_PORT);
    rightSlave.follow(rightMaster);
    rightSlave.configFactoryDefault();

    leftMaster = new WPI_TalonFX(MASTER_LEFT_PORT);
    leftMaster.configFactoryDefault();
    leftMaster.setInverted(true);

    leftSlave = new WPI_TalonFX(SLAVE_LEFT_PORT);
    leftSlave.follow(leftMaster);
    leftSlave.configFactoryDefault();
    leftSlave.setInverted(true);

    differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
    differentialDrive.setRightSideInverted(false);
    gyro = new PigeonIMU(rightMaster);

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getOdometryHeading()));
  }

  @Override
  public final IMotorController getRightSlaveMotor() {
    return rightSlave;
  }

  @Override
  public final WPI_TalonFX getRightMasterMotor() {
    return rightMaster;
  }

  @Override
  public final IMotorController getLeftSlaveMotor() {
    return leftSlave;
  }

  @Override
  public final WPI_TalonFX getLeftMasterMotor() {
    return leftMaster;
  }

  @Override
  public final DifferentialDrive getDifferentialDrive() {
    return differentialDrive;
  }
  @Override
  public final PigeonIMU getGyro() {
    return gyro;
  }

  @Override
  public final DifferentialDriveOdometry getOdometry() {
    return odometry;
  }

  public final double getOdometryHeading() {
    final double[] yawPitchRoll = new double[3];
    gyro.getYawPitchRoll(yawPitchRoll);
    return Math.IEEEremainder(yawPitchRoll[0], 360);
  }

  private TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.slot0.kP = DRIVE_BY_DISTANCE_P;
    config.slot0.kI = DRIVE_BY_DISTANCE_I;
    config.slot0.kD = DRIVE_BY_DISTANCE_D;
    config.slot0.kF = DriveTrainConstants.PERCENTAGE_CLOSED_LOOP_OUTPUT *
        DriveTrainConstants.MAX_CLOSED_LOOP_OUTPUT / DriveTrainConstants.MAX_VELOCITY;
    return config;
  }
}
