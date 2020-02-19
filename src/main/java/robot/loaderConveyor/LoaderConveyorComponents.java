package robot.loaderConveyor;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public interface LoaderConveyorComponents {

  WPI_VictorSPX getMasterMotor();
}
