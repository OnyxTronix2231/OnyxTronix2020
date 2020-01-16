package robot.conveyance;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.conveyance.ConveyorConstants.*;


public class BasicConveyorComponents implements ConveyorComponents {

    private final WPI_TalonSRX masterMotor;

    public BasicConveyorComponents() {
        masterMotor = new WPI_TalonSRX(ConveyorConstants.MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();
        masterMotor.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
        masterMotor.configPeakCurrentDuration(PICK_AMP_DURATION);
        masterMotor.configPeakCurrentLimit(PICK_AMP);
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.enableCurrentLimit(true);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }
}
