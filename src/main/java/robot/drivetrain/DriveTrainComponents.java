package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public interface DriveTrainComponents {

  IMotorController getRightSlaveMotor();

  WPI_TalonFX getRightMasterMotor();

  IMotorController getLeftSlaveMotor();

  WPI_TalonFX getLeftMasterMotor();

  DifferentialDrive getDifferentialDrive();
}
