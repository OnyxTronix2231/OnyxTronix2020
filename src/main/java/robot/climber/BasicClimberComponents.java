package robot.climber;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicClimberComponents implements ClimberComponents {

  private final WPI_TalonSRX masterMotor;
  private final VictorSPX slaveMotor;

  public BasicClimberComponents() {
    masterMotor = new WPI_TalonSRX(1);

    slaveMotor = new VictorSPX(2);
    slaveMotor.follow(masterMotor);
  }

  @Override
  public final WPI_TalonSRX getMasterMotor() {
    return masterMotor;
  }

  @Override
  public final VictorSPX getSlaveMotor() {
    return slaveMotor;
  }
}
