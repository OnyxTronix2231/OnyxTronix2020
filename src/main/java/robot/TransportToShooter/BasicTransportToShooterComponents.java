package robot.TransportToShooter;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import robot.conveyance.TransportToShoteerConstants;

public class BasicTransportToShooterComponents implements TransportToShooterComponents  {

    private final WPI_TalonSRX masterMotor;
    private final WPI_TalonSRX slaveMotor;

    public BasicTransportToShooterComponents() {
        this.masterMotor = new WPI_TalonSRX(TransportToShoteerConstants.SECOND_MASTER_MOTOR_PORT);

        this.slaveMotor = new WPI_TalonSRX(TransportToShoteerConstants.SECOND_SLAVE_MOTOR_PORT);
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
