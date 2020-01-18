package robot.LoaderConveyor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LoaderConveyor extends SubsystemBase {

    private final BasicLoaderConveyorComponents components;

    public LoaderConveyor(final BasicLoaderConveyorComponents moveBySpeed) {
        this.components = moveBySpeed;
    }

    public final void moveLoaderConveyorBySpeed(final double speed) {
        components.getMotor().set(speed);
    }

    public final void stopMotor() {
        components.getMotor().set(0);
    }

    public void setVelocity(final double velocity) {
        components.getMotor().set(ControlMode.Velocity, velocity);
    }
}
