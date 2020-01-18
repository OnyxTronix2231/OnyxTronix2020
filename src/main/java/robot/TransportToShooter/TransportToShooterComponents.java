package robot.TransportToShooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface TransportToShooterComponents {

    WPI_TalonSRX getMasterMotor();

    IMotorController getSlaveMotor();

}
