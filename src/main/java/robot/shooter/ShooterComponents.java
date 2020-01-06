package robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;

public interface ShooterComponents {
    IMotorController getMasterMotor();
    IMotorController getSlaveMotor();
}
