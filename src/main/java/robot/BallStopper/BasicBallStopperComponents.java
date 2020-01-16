package robot.BallStopper;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.BallStopper.BallStopperConstants.*;

public class BasicBallStopperComponents implements BallStopperComponents {

  private final WPI_TalonSRX masterMotor;

  public BasicBallStopperComponents() {
    masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
    masterMotor.configFactoryDefault();
    masterMotor.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
    masterMotor.configPeakCurrentDuration(PICK_AMP_DURATION);
    masterMotor.configPeakCurrentLimit(PICK_AMP);
    masterMotor.setNeutralMode(NeutralMode.Brake);
    masterMotor.enableCurrentLimit(true);
  }

    @Override
    public WPI_TalonSRX getMasterMotor() {
     return masterMotor;
    }

  }

