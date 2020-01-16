package robot.conveyance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor extends SubsystemBase {

    private final BasicConveyorComponents components;

    public Conveyor(final BasicConveyorComponents moveFirstConveyanceBySpeed) {
        this.components = moveFirstConveyanceBySpeed;
    }

    public final void moveConveyorBySpeed(final double speed) {
        components.getMasterMotor().set(speed);
    }

    public final void stopMotorConveyor() {
        components.getMasterMotor().set(0);
    }
}
