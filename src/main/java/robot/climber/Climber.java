package robot.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

    private final ClimberComponents components;

    public Climber(final ClimberComponents components) {
        this.components = components;
        CommandScheduler.getInstance().registerSubsystem(this);
    }

    public void moveBySpeed(double speed) {
        components.getMasterMotor().set(ControlMode.PercentOutput, speed);
    }

    public void stopMotor() {
        components.getMasterMotor().set(ControlMode.PercentOutput, 0);
    }
}
