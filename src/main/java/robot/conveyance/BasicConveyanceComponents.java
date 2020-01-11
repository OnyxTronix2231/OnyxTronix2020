package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicConveyanceComponents implements ConveyanceComponents{
    private final WPI_TalonSRX firstSlaveMotor;
    private final WPI_TalonSRX firstMasterMotor;
    private final WPI_TalonSRX secondMasterMotor;
    private final WPI_TalonSRX secondSlaveMotor;

    public BasicConveyanceComponents(IMotorController firstMasterMotor, WPI_TalonSRX firstSlaveMotor,
                                     WPI_TalonSRX secondMasterMotor, WPI_TalonSRX secondSlaveMotor) {
        this.firstMasterMotor = new WPI_TalonSRX(ConveyanceConstants.FIRST_MASTER_MOTOR_PORT);
        this.firstSlaveMotor =  new WPI_TalonSRX(ConveyanceConstants.FIRST_SLAVE_MOTOR_PORT);
        firstSlaveMotor.follow(firstMasterMotor);

        this.secondMasterMotor = new WPI_TalonSRX(ConveyanceConstants.SECOND_MASTER_MOTOR_PORT);
        this.secondSlaveMotor =  new WPI_TalonSRX(ConveyanceConstants.SECOND_SLAVE_MOTOR_PORT);
        secondSlaveMotor.follow(secondMasterMotor);

    }

    @Override
    public final TalonSRX getFirstMasterMotor() {
        return firstMasterMotor;
    }

    @Override
    public final IMotorController getFirstSlaveMotor() {
        return firstSlaveMotor;
    }

    @Override
    public final IMotorController getSecondMasterMotor() {
        return secondMasterMotor;
    }

    @Override
    public IMotorController getSecondSlaveMotor() {
        return secondSlaveMotor;
    }
}
