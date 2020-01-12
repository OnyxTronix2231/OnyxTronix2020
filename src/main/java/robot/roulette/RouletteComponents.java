package robot.roulette;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface RouletteComponents {

    WPI_TalonSRX getMasterMotor();

    DoubleSolenoid getDoubleSolenoid();

    ColorSensorV3 getColorSensorV3();
}
