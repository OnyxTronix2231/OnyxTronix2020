package robot.climber;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface ClimberComponents {

    IMotorController getMasterMotor();

    IMotorController getSlaveMotor();
}
