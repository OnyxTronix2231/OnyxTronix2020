package robot.ballCollector;

import static robot.ballCollector.BallCollectorConstants.MIN_AMP_FOR_ONE;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallCollector extends SubsystemBase {

    private final BallCollectorComponents components;
    private int ballCounter = 3;

    public BallCollector(final BallCollectorComponents components) {
        this.components = components;
        Shuffleboard.getTab("ballCollector").addNumber("ballCounter", () -> ballCounter);
    }

    public final void collectBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public final void stopMotor() {
        collectBySpeed(0);
    }

    public void openPistons() {
        components.getDoubleSolenoid().set(DoubleSolenoid.Value.kForward);
    }

    public void closePistons() {
        components.getDoubleSolenoid().set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isBallCollected(){
        return getAmp() > MIN_AMP_FOR_ONE;
    }

    public double getAmp(){
      return components.getMasterMotor().getStatorCurrent();
    }

    public boolean canCollect(){
        return ballCounter <= 5;
    }

    public int getBallCounter(){
        return ballCounter;
    }

    public void addBall(){
        ballCounter++;
    }

    public void removeBall(){
        ballCounter--;
    }
}
