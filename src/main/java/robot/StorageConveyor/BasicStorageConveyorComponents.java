package robot.StorageConveyor;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.StorageConveyor.StorageConveyorConstants.*;


public class BasicStorageConveyorComponents implements StorageConveyorComponents {

    private final WPI_TalonSRX Motor;

    public BasicStorageConveyorComponents() {
        Motor = new WPI_TalonSRX(StorageConveyorConstants.MASTER_MOTOR_PORT);
        Motor.configFactoryDefault();
        Motor.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
        Motor.configPeakCurrentDuration(PICK_AMP_DURATION);
        Motor.configPeakCurrentLimit(PICK_AMP);
        Motor.setNeutralMode(NeutralMode.Brake);
        Motor.enableCurrentLimit(true);
    }

    @Override
    public IMotorController getMotor() {
        return Motor;
    }
}
