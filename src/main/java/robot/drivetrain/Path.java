package robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

import java.util.List;

public class Path {

  private final boolean isForward;
  private final List<Pose2d> path;

  public Path(final boolean isForward, final List<Pose2d> path) {
    this.isForward = isForward;
    this.path = path;
  }

  public boolean getIsForward() {
    return isForward;
  }

  public List<Pose2d> getPath() {
    return path;
  }

  public Pose2d getReversedAngleEndingPose() {
    return new Pose2d(getFinishingPose().getTranslation().getX(), getFinishingPose().getTranslation().getY(),
        Rotation2d.fromDegrees(getFinishingPose().getRotation().getDegrees()));
  }

  private Pose2d getFinishingPose() {
    return path.get(path.size() - 1);
  }
}
