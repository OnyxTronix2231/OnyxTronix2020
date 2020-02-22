package robot.delayConveyor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DelayConveyor extends SubsystemBase {

    private final DelayConveyorComponents components;

    public DelayConveyor(final DelayConveyorComponents delayConveyorComponents) {
      this.components = delayConveyorComponents;
    }

    public void moveDelayConveyorBySpeed(final double speed) {
      components.getMotor().set(speed);
    }

    public void stopMotor() {
      moveDelayConveyorBySpeed(0);
    }
  }
