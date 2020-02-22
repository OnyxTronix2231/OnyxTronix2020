package robot.delayConveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.delayConveyor.DelayConveyor;

import java.util.function.DoubleSupplier;

public class MoveDelayConveyorBySpeed extends CommandBase {

  private final DelayConveyor delayConveyor;
  private final DoubleSupplier speedSupplier;

  public MoveDelayConveyorBySpeed(final DelayConveyor delayConveyor, final DoubleSupplier speedSupplier) {
    this.delayConveyor = delayConveyor;
    this.speedSupplier = speedSupplier;
    addRequirements(delayConveyor);
  }

  @Override
  public void execute() {
    delayConveyor.moveDelayConveyorBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    delayConveyor.stopMotor();
  }
}
