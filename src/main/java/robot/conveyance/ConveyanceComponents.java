package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public interface ConveyanceComponents {

    IMotorController getFirstMasterMotor();
    IMotorController getFirstSlaveMotor();
    IMotorController getSecondMasterMotor();
    IMotorController getSecondSlaveMotor();
}
