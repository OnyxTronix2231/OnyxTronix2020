package robot.conveyance;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface ConveyorComponents {

  WPI_TalonSRX getMasterMotor();

}
