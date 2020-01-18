package robot.LoaderConveyor;

import static robot.LoaderConveyor.LoaderConveyorConstants.MAX_CLOSED_LOOP_OUTPUT;
import static robot.LoaderConveyor.LoaderConveyorConstants.MAX_VELOCITY;
import static robot.LoaderConveyor.LoaderConveyorConstants.VELOCITY_D;
import static robot.LoaderConveyor.LoaderConveyorConstants.VELOCITY_I;
import static robot.LoaderConveyor.LoaderConveyorConstants.VELOCITY_P;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

public class Config {

  public Config() {

  }

  public TalonSRXConfiguration getConfiguration() {
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.slot0.kP = VELOCITY_P;
    config.slot0.kI = VELOCITY_I;
    config.slot0.kD = VELOCITY_D;
    config.slot0.kF = MAX_CLOSED_LOOP_OUTPUT / MAX_VELOCITY;
    return config;
  }
}
