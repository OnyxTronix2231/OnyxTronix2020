package robot.drivetrain;

public class Path {
  private final Pose[] poses;
  private final Pose endingPose;

  public Path(final Pose... poses) {
    this.poses = poses;
    endingPose = poses[poses.length - 1];
  }

  public Pose getPoseAt(int i) {
    return poses[i];
  }

  public Pose getEndingPose() {
    return endingPose;
  }
}
