package robot.ballCollector;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicBallCollectorComponents implements BallCollectorComponents {

    private final WPI_TalonSRX masterMotor;

    public BasicBallCollectorComponents() {
        masterMotor = new WPI_TalonSRX(1);
    }

    @Override
    public WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }
}
