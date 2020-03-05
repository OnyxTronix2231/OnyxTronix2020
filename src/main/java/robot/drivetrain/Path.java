package robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

import java.util.List;

public class Path {

  private final boolean isForward;
  private final List<Pose2d> path;
  private final Pose2d endingPose;

  public Path(final boolean isForward, final List<Pose2d> path) {
    this.isForward = isForward;
    this.path = path;
    endingPose = path.get(path.size() - 1);
  }

  public boolean getIsForward() {
    return isForward;
  }

  public List<Pose2d> getPath() {
    return path;
  }

  public Pose2d getEndingPose() {
    return endingPose;
  }
}
