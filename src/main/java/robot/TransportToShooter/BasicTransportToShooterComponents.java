package robot.TransportToShooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static robot.TransportToShooter.TransportToShooterConstants.*;

public class BasicTransportToShooterComponents implements TransportToShooterComponents  {

    private final WPI_TalonSRX masterMotor;
    private final WPI_TalonSRX slaveMotor;

    public BasicTransportToShooterComponents() {
        this.masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();
        masterMotor.enableCurrentLimit(true);
        masterMotor.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
        masterMotor.configPeakCurrentDuration(PICK_AMP_DURATION);
        masterMotor.configPeakCurrentLimit(PICK_AMP);
        masterMotor.setNeutralMode(NeutralMode.Brake);
        this.slaveMotor = new WPI_TalonSRX(SLAVE_MOTOR_PORT);
        slaveMotor.follow(masterMotor);
    }

    @Override
    public final WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public IMotorController getSlaveMotor() {
        return slaveMotor;
    }
}
