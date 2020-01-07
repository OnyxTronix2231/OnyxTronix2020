package robot.ballCollector;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallCollector extends SubsystemBase {

    private BallCollectorComponents components;

    public BallCollector(final BallCollectorComponents components) {
        this.components = components;
    }

    public void moveBySpeed (double speed){
        components.getMaster().set(speed);
    }
}
