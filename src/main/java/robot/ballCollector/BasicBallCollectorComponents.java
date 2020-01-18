package robot.ballCollector;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import static robot.ballCollector.BallCollectorConstants.PICK_AMP;
import static robot.ballCollector.BallCollectorConstants.PICK_AMP_DURATION;

public class BasicBallCollectorComponents implements BallCollectorComponents {

    private final WPI_TalonSRX masterMotor;
    private final DoubleSolenoid rightDoubleSolenoid = null;
    private final DoubleSolenoid leftDoubleSolenoid = null;

    public BasicBallCollectorComponents() {
        masterMotor = new WPI_TalonSRX(BallCollectorConstants.MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();
        masterMotor.configPeakCurrentLimit(PICK_AMP);
        masterMotor.configPeakCurrentDuration(PICK_AMP_DURATION);
        masterMotor.setNeutralMode(NeutralMode.Brake);

//        rightDoubleSolenoid = new DoubleSolenoid(BallCollectorConstants.DOUBLE_RIGHT_SOLENOID_FORWARD_PORT, BallCollectorConstants.DOUBLE_RIGHT_SOLENOID_REVERSE_PORT);
//        leftDoubleSolenoid = new DoubleSolenoid(BallCollectorConstants.DOUBLE_LEFT_SOLENOID_FORWARD_PORT, BallCollectorConstants.DOUBLE_LEFT_SOLENOID_REVERSE_PORT);
    }

    @Override
    public final WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public final DoubleSolenoid getRightDoubleSolenoid() {
        return rightDoubleSolenoid;
    }

    @Override
    public final DoubleSolenoid getLeftDoubleSolenoid() {
        return leftDoubleSolenoid;
    }
}
