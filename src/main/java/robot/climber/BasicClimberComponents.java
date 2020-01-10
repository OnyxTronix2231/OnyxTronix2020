package robot.climber;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class BasicClimberComponents implements ClimberComponents {

  private final WPI_TalonSRX masterMotor;

  private final WPI_VictorSPX slaveMotor;

  public BasicClimberComponents() {
    masterMotor = new WPI_TalonSRX(1);

    slaveMotor = new WPI_VictorSPX(2);
    slaveMotor.follow(masterMotor);

    DoubleSolenoid solenoid = new DoubleSolenoid(0, 1);
    solenoid.set(Value.kForward);
  }

  @Override
  public final WPI_TalonSRX getMaster() {
    return this.masterMotor;
  }

  @Override
  public final WPI_VictorSPX getSlave() {
    return this.slaveMotor;
  }
}
