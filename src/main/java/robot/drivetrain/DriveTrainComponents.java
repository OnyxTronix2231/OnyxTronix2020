package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;

public interface DriveTrainComponents {

  WPI_TalonFX getRightMasterMotor();

  IMotorController getRightSlaveMotor();

  WPI_TalonFX getLeftMasterMotor();

  IMotorController getLeftSlaveMotor();

  DifferentialDrive getDifferentialDrive();

  AHRS getGyro();

  DifferentialDriveOdometry getOdometry();
}
