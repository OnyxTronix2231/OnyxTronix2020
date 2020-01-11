package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicConveyanceComponents implements ConveyanceComponents {

    private final WPI_TalonSRX masterMotor;
    private final WPI_TalonSRX slaveMotor;

    public BasicConveyanceComponents() {
        masterMotor = new WPI_TalonSRX(ConveyanceConstants.FIRST_MASTER_MOTOR_PORT);

        slaveMotor = new WPI_TalonSRX(ConveyanceConstants.FIRST_SLAVE_MOTOR_PORT);
        slaveMotor.follow(masterMotor);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public IMotorController getSlaveMotor() {
        return slaveMotor;
    }
}
