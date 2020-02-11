package robot.loaderConveyor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LoaderConveyor extends SubsystemBase {

  private final BasicLoaderConveyorComponents components;

  public LoaderConveyor(final BasicLoaderConveyorComponents moveBySpeed) {
    this.components = moveBySpeed;
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
