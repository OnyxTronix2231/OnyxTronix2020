package robot.ConveytorOneBall.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.ConveytorOneBall.ConveyorOneBall;
import java.util.function.DoubleSupplier;

public class MoveConveyorOneBallBySpeed extends CommandBase {

  private final ConveyorOneBall conveyorOneBall;
  private final DoubleSupplier speedSupplier;

  public MoveConveyorOneBallBySpeed(final ConveyorOneBall conveyorOneBall, final DoubleSupplier speed) {
    this.conveyorOneBall = conveyorOneBall;
    this.speedSupplier = speed;
  }

  @Override
  public void initialize() {
    conveyorOneBall.moveConveyanceBySpeed(speedSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    conveyorOneBall.stopMotorFirstConveyance();
  }

}
