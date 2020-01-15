package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicConveyorComponents implements ConveyorComponents {

    private final WPI_TalonSRX masterMotor;

    public BasicConveyorComponents() {
        masterMotor = new WPI_TalonSRX(ConveyorConstants.MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();

    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }
}
