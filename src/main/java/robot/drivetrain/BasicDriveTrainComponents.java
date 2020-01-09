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
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class BasicDriveTrainComponents implements DriveTrainComponents {
  private final WPI_TalonFX rightSlave;
  private final WPI_TalonFX rightMaster;
  private final WPI_TalonFX leftSlave;
  private final WPI_TalonFX leftMaster;
  private final DifferentialDrive differentialDrive;

  public BasicDriveTrainComponents() {
    rightMaster = new WPI_TalonFX(MASTER_RIGHT_PORT);
    rightMaster.configFactoryDefault();
    rightMaster.configAllSettings(getConfiguration());

    rightSlave = new WPI_TalonFX(SLAVE_RIGHT_PORT);
    rightSlave.follow(rightMaster);
    rightSlave.configFactoryDefault();

    leftMaster = new WPI_TalonFX(MASTER_LEFT_PORT);
    leftMaster.configFactoryDefault();
    leftMaster.configAllSettings(getConfiguration());

    leftSlave = new WPI_TalonFX(SLAVE_LEFT_PORT);
    leftSlave.follow(leftMaster);
    leftSlave.configFactoryDefault();

    differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
  }

  private TalonFXConfiguration getConfiguration() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.slot0.kP = DRIVE_BY_DISTANCE_P;
    config.slot0.kI = DRIVE_BY_DISTANCE_I;
    config.slot0.kD = DRIVE_BY_DISTANCE_D;
    config.slot0.kF = PERCENTAGE_CLOSED_LOOP_OUTPUT *
        MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    return config;
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
}
