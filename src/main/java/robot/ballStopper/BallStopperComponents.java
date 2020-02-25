package robot.ballStopper;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public interface BallStopperComponents {

  IMotorController getBallStopperMotor();

  IMotorController getDelayMotor();
}
