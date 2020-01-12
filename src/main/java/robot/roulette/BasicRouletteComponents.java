package robot.roulette;

import static robot.roulette.RouletteConstants.DOUBLE_SOLENOID_FORWARD_CHANNEL;
import static robot.roulette.RouletteConstants.DOUBLE_SOLENOID_REVERSE_CHANNEL;
import static robot.roulette.RouletteConstants.MASTER_MOTOR_PORT;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;

public class BasicRouletteComponents implements RouletteComponents {

    private final WPI_TalonSRX masterMotor;
    private final DoubleSolenoid doubleSolenoid;
    private final ColorSensorV3 colorSensorV3;

    public BasicRouletteComponents() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
        doubleSolenoid = new DoubleSolenoid(DOUBLE_SOLENOID_FORWARD_CHANNEL, DOUBLE_SOLENOID_REVERSE_CHANNEL);
        colorSensorV3 = new ColorSensorV3(I2C.Port.kOnboard);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public DoubleSolenoid getDoubleSolenoid() {
        return doubleSolenoid;
    }

    @Override
    public ColorSensorV3 getColorSensorV3() {
        return colorSensorV3;
    }
}
