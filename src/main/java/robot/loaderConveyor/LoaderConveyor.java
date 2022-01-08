package robot.loaderConveyor;

import static robot.loaderConveyor.LoaderConveyorConstants.LoaderConveyorComponentsA.DISTANCE_WITH_BALL;
import static robot.loaderConveyor.LoaderConveyorConstants.TOLERANCE;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LoaderConveyor extends SubsystemBase {

  private final LoaderConveyorComponents components;

  public LoaderConveyor(final LoaderConveyorComponents components) {
    this.components = components;
  }

  @Override
  public void periodic() {
    if(!components.getDistanceSensor().isRangeValid()) {
      components.reinitializeDistanceSensor();
    }
  }

  public final void moveLoaderConveyorBySpeed(final double speed) {
    components.getMasterMotor().set(speed);
  }

  public void moveLoaderConveyorByVelocity(final double velocity) {
    components.getMasterMotor().set(ControlMode.Velocity, velocity);
  }

  public final void stopMotor() {
    moveLoaderConveyorBySpeed(0);
  }

  public boolean isOnTarget() {
    return components.getMasterMotor().getClosedLoopError() < TOLERANCE;
  }

  public boolean isBallInLoader() {
      return (components.getCurrentDistance() <= DISTANCE_WITH_BALL);
  }
}
