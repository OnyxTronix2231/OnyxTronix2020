package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_D;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_I;
import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_P;

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

  public BasicDriveTrainComponents() {
    rightMaster = new WPI_TalonSRX(2);
    rightMaster.configFactoryDefault();
    rightMaster.configAllSettings(getConfiguration());

    rightSlave = new WPI_VictorSPX(1);
    rightSlave.follow(rightMaster);

    leftMaster = new WPI_TalonSRX(4);
    leftMaster.configFactoryDefault();
    leftMaster.configAllSettings(getConfiguration());

    leftSlave = new WPI_VictorSPX(3);
    leftSlave.follow(leftMaster);

    differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

    gyro = new PigeonIMU(rightMaster);
    final double[] yawPichRoll = new double[3];
    gyro.getYawPitchRoll(yawPichRoll);

    final double odometryHeading = Math.IEEEremainder(yawPichRoll[0], 360);

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(odometryHeading));
  }

  @Override
  public final IMotorController getRightSlaveMotor() {
    return rightSlave;
  }

  @Override
  public final WPI_TalonSRX getRightMasterMotor() {
    return rightMaster;
  }

  @Override
  public final IMotorController getLeftSlaveMotor() {
    return leftSlave;
  }

  @Override
  public final WPI_TalonSRX getLeftMasterMotor() {
    return leftMaster;
  }

  public final DifferentialDrive getDifferentialDrive() {
    return differentialDrive;
  }

  @Override
  public final PigeonIMU grtGyro() {
    return gyro;
  }

  @Override
  public final DifferentialDriveOdometry getOdometry() {
    return odometry;
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
