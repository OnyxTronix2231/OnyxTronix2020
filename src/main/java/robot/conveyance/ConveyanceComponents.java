package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface ConveyanceComponents {

    WPI_TalonSRX getMasterMotor();
    
    IMotorController getSlaveMotor();

}
