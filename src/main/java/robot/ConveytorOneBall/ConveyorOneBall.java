package robot.ConveytorOneBall;


public class ConveyorOneBall {

  private final BasicConveyorOneBallComponents conveyorOneBallComponents;

  public ConveyorOneBall(final BasicConveyorOneBallComponents conveyorOneBallComponents) {
    this.conveyorOneBallComponents = conveyorOneBallComponents;
  }

  public final void moveConveyanceBySpeed(final double speed) {
    conveyorOneBallComponents.getMasterMotor().set(speed);
  }

  public final void stopMotorFirstConveyance() {
    conveyorOneBallComponents.getMasterMotor().set(0);
  }
}

