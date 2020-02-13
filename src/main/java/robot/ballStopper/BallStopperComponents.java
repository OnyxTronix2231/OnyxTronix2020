package robot.ballStopper;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface BallStopperComponents {

  WPI_TalonSRX getMotor();
}