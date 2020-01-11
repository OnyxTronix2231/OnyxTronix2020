package robot.conveyance;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicConveyanceComponents implements ConveyanceComponents {
    private final WPI_TalonSRX firstSlaveMotor;
    private final WPI_TalonSRX firstMasterMotor;

    public BasicConveyanceComponents() {
        this.firstMasterMotor = new WPI_TalonSRX(ConveyanceConstants.FIRST_MASTER_MOTOR_PORT);
        this.firstSlaveMotor = new WPI_TalonSRX(ConveyanceConstants.FIRST_SLAVE_MOTOR_PORT);
        firstSlaveMotor.follow(firstMasterMotor);
    }

    @Override
    public final WPI_TalonSRX getMasterMotor() {
        return firstMasterMotor;
    }

    @Override
    public final WPI_TalonSRX getSlaveMotor() {
        return firstSlaveMotor;
    }

}