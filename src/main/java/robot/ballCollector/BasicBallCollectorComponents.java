package robot.ballCollector;

import static robot.climber.ClimberConstants.CONTINUOUS_CURRENT_LIMIT;
import static robot.climber.ClimberConstants.PICK_AMP;
import static robot.climber.ClimberConstants.PICK_AMP_DURATION;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BasicBallCollectorComponents implements BallCollectorComponents {

    private final WPI_TalonSRX masterMotor;

    public BasicBallCollectorComponents() {
        masterMotor = new WPI_TalonSRX(BallCollectorConstants.MASTER_MOTOR_PORT);
        masterMotor.configFactoryDefault();
        masterMotor.configPeakCurrentLimit(BallCollectorConstants.PICK_AMP);
        masterMotor.configPeakCurrentDuration(BallCollectorConstants.PICK_AMP_DURATION);
        masterMotor.configContinuousCurrentLimit(BallCollectorConstants.CONTINUOUS_CURRENT_LIMIT);
        masterMotor.enableCurrentLimit(true);
        masterMotor.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public final WPI_TalonSRX getMasterMotor() {
        return masterMotor;
    }
}
