package robot.conveyance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class StorageConveyor extends SubsystemBase {

  private final BasicConveyorComponents components;

  public StorageConveyor(final BasicConveyorComponents moveFirstConveyorBySpeed, final BasicSecondaryConveyor secondaryComponents) {
    this.components = moveFirstConveyorBySpeed;
  }

  public final void moveConveyorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public final void stopMotorFirstConveyor() {
    components.getMasterMotor().set(0);
  }
}
