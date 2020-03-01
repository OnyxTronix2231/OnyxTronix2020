package robot.shooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

public interface ShooterComponents {

  WPI_TalonFX getLeftMasterMotor();

  WPI_TalonFX getRightMasterMotor();

  Solenoid getSolenoid();
}
