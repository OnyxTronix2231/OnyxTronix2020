package robot.loaderConveyor;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface LoaderConveyorComponents {

    WPI_TalonSRX getMasterMotor();

    IMotorController getSlaveMotor();
    
}
