package robot.ballCollector;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import static robot.ballCollector.BallCollectorConstants.CONTINUOUS_CURRENT_LIMIT;
import static robot.ballCollector.BallCollectorConstants.MASTER_MOTOR_PORT;
import static robot.ballCollector.BallCollectorConstants.PICK_AMP;
import static robot.ballCollector.BallCollectorConstants.PICK_AMP_DURATION;

public class BasicBallCollectorComponents implements BallCollectorComponents {

    private final WPI_TalonSRX masterMotor;
    private final DoubleSolenoid doubleSolenoid;

    public BasicBallCollectorComponents() {
        masterMotor = new WPI_TalonSRX(MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();
        masterMotor.configPeakCurrentLimit(PICK_AMP);
        masterMotor.configPeakCurrentDuration(PICK_AMP_DURATION);
        masterMotor.configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
        masterMotor.enableCurrentLimit(true);
        masterMotor.setNeutralMode(NeutralMode.Brake);
        masterMotor.setInverted(true);

        doubleSolenoid = new DoubleSolenoid(BallCollectorConstants.DOUBLE_SOLENOID_FORWARD_PORT, BallCollectorConstants.DOUBLE_SOLENOID_REVERSE_PORT);
    }

    @Override
    public final WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }

    @Override
    public DoubleSolenoid getDoubleSolenoid() {
        return doubleSolenoid;
    }
}
