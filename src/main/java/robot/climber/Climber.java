package robot.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

    private ClimberComponents components;

    public Climber (final ClimberComponents components) {
        this.components = components;
    }

    public void moveBySpeed (double speed) {
        components.getMaster().set(ControlMode.PercentOutput, speed);
    }

}
