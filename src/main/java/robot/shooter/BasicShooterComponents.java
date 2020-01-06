package robot.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicShooterComponents implements ShooterComponents {
    private IMotorController masterMotor;
    private IMotorController slaveMotor;
    public BasicShooterComponents (){
        masterMotor = new WPI_TalonSRX(1);
        slaveMotor = new WPI_TalonSRX(2);

        slaveMotor.set(ControlMode.Follower,1);
    }

    @Override
    public IMotorController getMasterMotor() {
        return masterMotor;
    }

    @Override
    public IMotorController getSlaveMotor() {
        return slaveMotor;
    }
}
