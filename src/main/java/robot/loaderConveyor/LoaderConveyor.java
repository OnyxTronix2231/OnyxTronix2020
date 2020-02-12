package robot.loaderConveyor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LoaderConveyor extends SubsystemBase {

  private final BasicLoaderConveyorComponentsA components;

  public LoaderConveyor(final BasicLoaderConveyorComponentsA components) {
    this.components = components;
  }

  public final void moveLoaderConveyorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void setVelocity(final double velocity) {
    components.getMasterMotor().set(ControlMode.Velocity, velocity);
  }

  public final void stopMotor() {
    components.getMasterMotor().set(0);
  }
}