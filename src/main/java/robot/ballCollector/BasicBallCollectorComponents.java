package robot.ballCollector;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class BasicBallCollectorComponents implements BallCollectorComponents {

    private final WPI_TalonSRX masterMotor;
    private final DoubleSolenoid rightDoubleSolenoid;
    private final DoubleSolenoid leftDoubleSolenoid;

    public BasicBallCollectorComponents() {
        masterMotor = new WPI_TalonSRX(BallCollectorConstants.MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();

        rightDoubleSolenoid = new DoubleSolenoid(BallCollectorConstants.DOUBLE_RIGHT_SOLENOID_FORWARD_PORT, BallCollectorConstants.DOUBLE_RIGHT_SOLENOID_REVERSE_PORT);
        leftDoubleSolenoid = new DoubleSolenoid(BallCollectorConstants.DOUBLE_LEFT_SOLENOID_FORWARD_PORT, BallCollectorConstants.DOUBLE_LEFT_SOLENOID_REVERSE_PORT);
    }

    @Override
    public final WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }
    @Override
    public final DoubleSolenoid getRightDoubleSolenoid(){
        return rightDoubleSolenoid;
    }
    @Override
    public final DoubleSolenoid getLeftDoubleSolenoid(){
        return leftDoubleSolenoid;
    }
}
