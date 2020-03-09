package robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;

public class Pose {

  private Pose2d pose2d;
  private boolean isForward;

  public Pose(Pose2d pose2d, boolean isForward) {
    this.pose2d = pose2d;
    this.isForward = isForward;
  }


  public Pose2d getPose2d() {
    return pose2d;
  }

  public boolean isForward() {
    return isForward;
  }
}
