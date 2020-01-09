package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public interface ConveyanceComponents {

    IMotorController getMasterMotor();
    IMotorController getSlaveMotor();



}
