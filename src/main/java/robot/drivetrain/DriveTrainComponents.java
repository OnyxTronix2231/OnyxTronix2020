package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;

public interface DriveTrainComponents {

  IMotorController getRightSlaveMotor();

  WPI_TalonSRX getRightMasterMotor();

  IMotorController getLeftSlaveMotor();

  WPI_TalonSRX getLeftMasterMotor();

  DifferentialDrive getDifferentialDrive();

  PigeonIMU grtGyro();

  DifferentialDriveOdometry getOdometry();
}
