package robot.yawControl.commands;

import robot.yawControl.YawControl;
import vision.limelight.Limelight;

public class AlignByFieldOrientationUntilTargetFound extends MoveToAngleRTF{

  private final YawControl yawControl;

  public AlignByFieldOrientationUntilTargetFound(YawControl yawControl) {
    super(yawControl, () -> 0);
    this.yawControl = yawControl;
  }

  @Override
  public boolean isFinished() {
    return Limelight.getInstance().targetFound();
  }
}
