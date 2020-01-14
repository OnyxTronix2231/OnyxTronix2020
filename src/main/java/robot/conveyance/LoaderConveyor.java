package robot.conveyance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LoaderConveyor extends SubsystemBase {

  private final BasicLoaderConveyorComponents components;

  public LoaderConveyor(final BasicLoaderConveyorComponents components) {
    this.components = components;
  }

  public void moveConveyorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void stopMotorStorageConveyor() {
    moveConveyorBySpeed(0);
  }
}
