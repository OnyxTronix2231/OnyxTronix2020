package robot.ballCollector;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicBallCollectorComponents implements BallCollectorComponents {

    private final WPI_TalonSRX masterMotor;

    public BasicBallCollectorComponents() {
        masterMotor = new WPI_TalonSRX(BallCollectorConstant.MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();
    }

    @Override
    public final WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }
}
