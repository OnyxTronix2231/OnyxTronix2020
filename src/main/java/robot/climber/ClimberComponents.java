package robot.climber;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface ClimberComponents {

  WPI_TalonSRX getMasterMotor();

  IMotorController getSlaveMotor();

  DoubleSolenoid getLeftDoubleSolenoid();

  DoubleSolenoid getRightDoubleSolenoid();


}
