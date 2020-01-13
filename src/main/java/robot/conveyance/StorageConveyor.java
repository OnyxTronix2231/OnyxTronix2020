package robot.conveyance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class StorageConveyor extends SubsystemBase {

  private final BasicStorageConveyorComponents components;

  public StorageConveyor(final BasicStorageConveyorComponents components) {
    this.components = components;
  }

  public final void moveConveyorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public final void stopMotorStorageConveyor() {
    moveConveyorBySpeed(0);
  }
}
