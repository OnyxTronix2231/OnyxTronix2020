package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public interface DriveTrainComponents {

    IMotorController getRightSlaveMotor();

    WPI_TalonSRX getRightMasterMotor();

    IMotorController getLeftSlaveMotor();

    WPI_TalonSRX getLeftMasterMotor();

    DifferentialDrive getDifferentialDrive();


}
