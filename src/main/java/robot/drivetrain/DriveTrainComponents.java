package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;

public interface DriveTrainComponents {

  IMotorController getRightSlaveMotor();

  WPI_TalonFX getRightMasterMotor();

  IMotorController getLeftSlaveMotor();

  WPI_TalonFX getLeftMasterMotor();

  DifferentialDrive getDifferentialDrive();

  PigeonIMU getGyro();

  DifferentialDriveOdometry getOdometry();
}
