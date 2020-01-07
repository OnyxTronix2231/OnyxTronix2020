package robot.climber;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface ClimberComponents {

    WPI_TalonSRX getMaster();

    VictorSPX getSlave();
}
