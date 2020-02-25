package robot.yawControl.commands;

import robot.yawControl.YawControl;
import vision.limelight.Limelight;

public class AlignByFieldOrientationUntilVisionTargetFound extends MoveToAngleRTF{

  public AlignByFieldOrientationUntilVisionTargetFound(YawControl yawControl) {
    super(yawControl, () -> 0);
  }

  @Override
  public boolean isFinished() {
    return Limelight.getInstance().targetFound();
  }
}
