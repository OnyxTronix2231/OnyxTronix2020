package robot.ballStopper;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public interface BallStopperComponents {

  WPI_TalonSRX getBallStopperMotor();

  WPI_TalonSRX getDelayMotor();
}
