package robot.LoaderConveyor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface LoaderConveyorComponents {

    WPI_TalonSRX getMasterMotor();
    WPI_TalonSRX getSlaveMotor();

}
