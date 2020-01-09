package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import static robot.drivetrain.DriveTrainConstants.*;

public class BasicDriveTrainComponents implements DriveTrainComponents {
    private final WPI_VictorSPX rightSlave;
    private final WPI_TalonSRX rightMaster;
    private final WPI_VictorSPX leftSlave;
    private final WPI_TalonSRX leftMaster;
    private final DifferentialDrive differentialDrive;

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

    @Override
    public IMotorController getRightSlaveMotor() {
        return rightSlave;
    }

    @Override
    public WPI_TalonSRX getRightMasterMotor() {
        return rightMaster;
    }

    @Override
    public IMotorController getLeftSlaveMotor() {
        return leftSlave;
    }

    @Override
    public WPI_TalonSRX getLeftMasterMotor() {
        return leftMaster;
    }

    public DifferentialDrive getDifferentialDrive() {
        return differentialDrive;
    }
}
