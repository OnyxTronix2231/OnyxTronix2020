package robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;

import java.util.List;

public class Path {

  private final boolean isForward;
  private final List<Pose2d> path;
  private final Pose2d startingPose;

  public Path(final boolean isForward, final List<Pose2d> path) {
    this.isForward = isForward;
    this.path = path;
    this.startingPose = path.get(0);
  }

  public Path(final boolean isForward, final List<Pose2d> path, final Pose2d startingPose) {
    this.isForward = isForward;
    this.path = path;
    this.startingPose = startingPose;
  }

  public boolean getIsForward() {
    return isForward;
  }

  public List<Pose2d> getPath() {
    return path;
  }

  public Pose2d getStartingPose() {
    return startingPose;
  }
}
