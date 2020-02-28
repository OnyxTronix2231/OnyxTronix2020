package robot.yawControl.commands;

import robot.yawControl.YawControl;
import vision.limelight.Limelight;

public class AlignByFieldOrientation extends MoveToAngleRTF {

  private final YawControl yawControl;
  private double counter;

  public AlignByFieldOrientation(YawControl yawControl) {
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
    if (yawControl.isOnTarget()) {
      counter++;
    } else {
      counter = 0;
    }
  }

  @Override
  public boolean isFinished() {
    return yawControl.isOnTarget() && counter > 5;
  }
}
