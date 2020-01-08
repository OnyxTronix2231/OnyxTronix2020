package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public interface DriveTrainComponents {

    IMotorController getFirstRightSlave();

    WPI_TalonSRX getRightMaster();

    IMotorController getFirstLeftSlave();

    WPI_TalonSRX getLeftMaster();

    DifferentialDrive getDifferentialDrive();


}
