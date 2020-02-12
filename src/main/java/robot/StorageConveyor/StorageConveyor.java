package robot.StorageConveyor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class StorageConveyor extends SubsystemBase {

  private final StorageConveyorComponents components;

  public StorageConveyor(final StorageConveyorComponents components) {
    this.components = components;
  }

  public final void moveStorageConveyorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public final void stopMotor() {
    moveStorageConveyorBySpeed(0);
  }
}
