package robot.climber;

import com.ctre.phoenix.motorcontrol.IMotorController;

public interface ClimberComponents {

    IMotorController getMasterMotor();

    IMotorController getSlaveMotor();
}
