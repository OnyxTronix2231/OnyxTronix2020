package robot.conveyance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyance extends SubsystemBase {

    private final BasicConveyanceComponents components;

    public Conveyance(final BasicConveyanceComponents moveFirstConveyanceBySpeed) {
        this.components = moveFirstConveyanceBySpeed;
    }

    public final void moveConveyanceBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public final void stopMotorFirstConveyance() {
        components.getMasterMotor().set(0);
    }
}
