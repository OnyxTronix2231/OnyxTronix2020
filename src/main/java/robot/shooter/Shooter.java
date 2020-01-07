package robot.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private ShooterComponents components;
    public Shooter (ShooterComponents components){
        this.components = components;
    }
    public void moveBySpeed(final double speed){
        components.getMasterMotor().set(ControlMode.PercentOutput, speed);
    }

    public int getEncoder() {
        return components.getMasterMotor().getSelectedSensorPosition(0);
    }
}
