package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

public interface DriveTrainComponents {

  WPI_TalonFX getRightMasterMotor();

  IMotorController getRightSlaveMotor();

  WPI_TalonFX getLeftMasterMotor();

  IMotorController getLeftSlaveMotor();

  NormalizedPigeonIMU getPigeonIMU();

  DifferentialDrive getDifferentialDrive();

  DifferentialDriveOdometry getOdometry();

  SimpleMotorFeedforward getMotorFeedForward();

  DifferentialDriveKinematics getDriveKinematics();

  DifferentialDriveVoltageConstraint getAutonomousVoltage();

  TrajectoryConfig getTrajectoryConfig();

  OnyxTrajectoryGenerator getTrajectoryGenerator();
}
