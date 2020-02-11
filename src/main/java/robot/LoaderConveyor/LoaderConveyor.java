package robot.loaderConveyor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LoaderConveyor extends SubsystemBase {

  private final robot.loaderConveyor.BasicLoaderConveyorComponents components;

  public LoaderConveyor(final robot.loaderConveyor.BasicLoaderConveyorComponents components) {
    this.components = components;
  }

  public final void moveLoaderConveyorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public final void stopMotor() {
    components.getMasterMotor().set(0);
  }

  public void setVelocity(final double velocity) {
    components.getMasterMotor().set(ControlMode.Velocity, velocity);
  }
}
