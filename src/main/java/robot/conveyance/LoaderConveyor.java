package robot.conveyance;

public class LoaderConveyor {

  private final BasicSecondaryConveyor components;

  public LoaderConveyor(final BasicSecondaryConveyor moveFirstConveyorBySpeed, final BasicSecondaryConveyor secondaryComponents) {
    this.components = moveFirstConveyorBySpeed;
  }

  public final void moveConveyorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public final void stopMotorStorageConveyor() {
    components.getMasterMotor().set(0);
  }
}



