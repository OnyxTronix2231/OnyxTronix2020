package robot.ballCollector;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface BallCollectorComponents {
    WPI_TalonSRX getMasterMotor();

    DoubleSolenoid getDoubleSolenoid();
}

