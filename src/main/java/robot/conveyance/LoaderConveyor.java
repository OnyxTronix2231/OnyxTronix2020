package robot.conveyance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LoaderConveyor extends SubsystemBase {

  private final BasicSecondaryConveyor components;

  public LoaderConveyor(final BasicSecondaryConveyor moveFirstConveyorBySpeed) {
    this.components = moveFirstConveyorBySpeed;
  }

  public final void moveConveyorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public final void stopMotorStorageConveyor() {
    components.getMasterMotor().set(0);
  }
}



