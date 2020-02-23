package robot.ballCollector;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public interface BallCollectorComponents {

  WPI_TalonSRX getMasterMotor();

  Solenoid getSolenoid();
}
