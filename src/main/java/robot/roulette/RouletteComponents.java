package robot.roulette;

import com.ctre.phoenix.motorcontrol.IMotorController;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface RouletteComponents {

    IMotorController getController();

    DoubleSolenoid getDoubleSolenoid();
}
