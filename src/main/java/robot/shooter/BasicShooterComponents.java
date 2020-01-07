package robot.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class BasicShooterComponents implements ShooterComponents {

    private WPI_TalonSRX masterMotor;
    private IMotorController slaveMotor;

    public BasicShooterComponents (){
        masterMotor = new WPI_TalonSRX(1);

        slaveMotor = new WPI_VictorSPX(2);
        slaveMotor.follow(masterMotor);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public IMotorController getSlaveMotor() {
        return slaveMotor;
    }
}
