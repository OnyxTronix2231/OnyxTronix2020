package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class BasicDriveTrainComponents implements DriveTrainComponents {


    private final IMotorController firstRightSlave;
    private final WPI_TalonSRX rightMaster;
    private final IMotorController firstLeftSlave;
    private final WPI_TalonSRX leftMaster;
    private final DifferentialDrive differentialDrive;

    public BasicDriveTrainComponents() {
        rightMaster = new WPI_TalonSRX(2);
        rightMaster.configFactoryDefault();
        rightMaster.configAllSettings(getConfiguration());

        firstRightSlave = new VictorSPX(1);
        firstRightSlave.follow(rightMaster);

        leftMaster = new WPI_TalonSRX(4);
        leftMaster.configFactoryDefault();
        leftMaster.configAllSettings(getConfiguration());

        firstLeftSlave = new VictorSPX(3);
        firstLeftSlave.follow(leftMaster);

        this.differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

    }

    private TalonSRXConfiguration getConfiguration() {
        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.slot0.kP = 1;
        config.slot0.kI = 0;
        config.slot0.kD = 0;
        config.slot0.kF = DriveTrainConstants.PERCENTAGE_CLOSED_LOOP_OUTPUT *
                DriveTrainConstants.MAX_CLOSED_LOOP_OUTPUT / DriveTrainConstants.MAX_VELOCITY;
        return config;
    }

    @Override
    public IMotorController getFirstRightSlave() {
        return firstRightSlave;
    }

    @Override
    public WPI_TalonSRX getRightMaster() {
        return rightMaster;
    }

    @Override
    public IMotorController getFirstLeftSlave() {
        return firstLeftSlave;
    }

    @Override
    public WPI_TalonSRX getLeftMaster() {
        return leftMaster;
    }

    @Override
    public DifferentialDrive getDifferentialDrive() {
        return differentialDrive;
    }
}
