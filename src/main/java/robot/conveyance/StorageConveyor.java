package robot.conveyance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class StorageConveyor extends SubsystemBase {

  private final BasicConveyorComponents components;

  public StorageConveyor(final BasicConveyorComponents storageComponents) {
    this.components = storageComponents;
  }

  public final void moveConveyorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public final void stopMotorStorageConveyor() {
    components.getMasterMotor().set(0);
  }
}
