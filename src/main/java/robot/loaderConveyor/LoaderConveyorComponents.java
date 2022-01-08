package robot.loaderConveyor;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.Rev2mDistanceSensor;

public interface LoaderConveyorComponents {

  WPI_TalonSRX getMasterMotor();

  Rev2mDistanceSensor getDistanceSensor();

  double getCurrentDistance();

  void reinitializeDistanceSensor();
}
