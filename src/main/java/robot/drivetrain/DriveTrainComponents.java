package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public interface DriveTrainComponents {

  WPI_TalonFX getRightMasterMotor();

  IMotorController getRightSlaveMotor();

  WPI_TalonFX getLeftMasterMotor();

  IMotorController getLeftSlaveMotor();

  AHRS getNavX();

  PIDController getGyroPIDController();

  DifferentialDrive getDifferentialDrive();
}
