package robot.ballStopper;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.ballStopper.BallStopperConstants.BallStopperComponents.*;

public class BasicBallStopperComponents implements BallStopperComponents {

  private final WPI_TalonSRX Motor;

  public BasicBallStopperComponents() {
    Motor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    Motor.configFactoryDefault();
    Motor.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
    Motor.configPeakCurrentDuration(PICK_AMP_DURATION);
    Motor.configPeakCurrentLimit(PICK_AMP);
    Motor.setNeutralMode(NeutralMode.Brake);
    Motor.enableCurrentLimit(true);
  }

    @Override
    public WPI_TalonSRX getMotor() {
     return Motor;
    }

  }

