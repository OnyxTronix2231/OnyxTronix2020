package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicConveyanceComponents implements ConveyanceComponents{
    private final IMotorController slaveMotor;
    private final TalonSRX masterMotor;

    public BasicConveyanceComponents( IMotorController firstRightSlave, WPI_TalonSRX rightMaster) {
        masterMotor = new TalonSRX(7);

        slaveMotor =  new TalonSRX(8);
        slaveMotor.follow(masterMotor);
    }

    @Override
    public TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public IMotorController getSlaveMotor() {
        return slaveMotor;
    }
}
