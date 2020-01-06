package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class BasicDriveTrainComponents implements DriveTrainComponents {

    private IMotorController firstRightSlave;
    private WPI_TalonSRX rightMaster;
    private IMotorController firstLeftSlave;
    private WPI_TalonSRX leftMaster;
    private DifferentialDrive differentialDrive;



    public BasicDriveTrainComponents(){
        rightMaster = new WPI_TalonSRX(2);
        rightMaster.configFactoryDefault();
        firstRightSlave = new VictorSPX(1);
        leftMaster = new WPI_TalonSRX(4);
        leftMaster.configFactoryDefault();
        firstLeftSlave = new VictorSPX(3);

        firstRightSlave.set(ControlMode.Follower,2);
        firstLeftSlave.set(ControlMode.Follower,4);

        this.differentialDrive=new DifferentialDrive( leftMaster,rightMaster);



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
