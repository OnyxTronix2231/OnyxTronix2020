package robot.yawControl.commands;

import robot.yawControl.YawControl;
import vision.limelight.Limelight;

public class AlignByFieldOrientationUntilTargetFound extends MoveToAngleRTF{

  private final YawControl yawControl;
  private double counter;

  public AlignByFieldOrientationUntilTargetFound(YawControl yawControl) {
    super(yawControl, () -> 0);
    this.yawControl = yawControl;
  }

  @Override
  public void initialize() {
    super.initialize();
    counter = 0;
  }

  @Override
  public void execute() {
    super.execute();
    if(yawControl.isOnTarget())
      counter++;
  }

  @Override
  public boolean isFinished() {
    return yawControl.isOnTarget() && counter > 10;
  }
}
