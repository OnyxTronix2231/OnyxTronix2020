package robot.autonomous;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import robot.drivetrain.Path;

import java.util.List;

public final class AutonomousConstants {

  public static final double AUTONOMOUS_DISTANCE = 25;
  public static final double DRIVE_AUTONOMOUS_TIMEOUT = 5;
  public static final double SHOOTER_TIMER = 3;

  public static final class Paths {

    public static final Path[][] PATHS = {START_INFRONT_OF_TARGET_COLLECT_THREE_BALLS_FROM_SHIELD_GENERATOR(),
        START_INFRONT_OF_TRENCH_COLLECT_TWO_BALLS_SHOOT_COLLECT_THREE_MORE()};

    public static Path[] START_INFRONT_OF_TARGET_COLLECT_THREE_BALLS_FROM_SHIELD_GENERATOR() {
      final List<Pose2d> subPathOnePoints = List.of(
          new Pose2d(3.2, 2.4, Rotation2d.fromDegrees(90)),
          new Pose2d(5.8, 4.4, Rotation2d.fromDegrees(-20))
      );
      final Path subPathOne = new Path(true, subPathOnePoints);

      final List<Pose2d> subPathTwoPoints = List.of(
          subPathOne.getEndingPose(),
          new Pose2d(5, 4.3, Rotation2d.fromDegrees(20))
      );
      final Path subPathTwo = new Path(false, subPathTwoPoints);

      final List<Pose2d> subPathThreePoints = List.of(
          subPathTwo.getEndingPose(),
          new Pose2d(5.6, 3.9, Rotation2d.fromDegrees(-20))
      );
      final Path subPathThree = new Path(true, subPathThreePoints);

      final List<Pose2d> subPathFourPoints = List.of(
          subPathThree.getEndingPose(),
          new Pose2d(3.65, 3, Rotation2d.fromDegrees(120))
      );
      final Path subPathFour = new Path(false, subPathFourPoints);

      return new Path[]{subPathOne, subPathTwo, subPathThree, subPathFour};
    }

    static Path[] START_INFRONT_OF_TRENCH_COLLECT_TWO_BALLS_SHOOT_COLLECT_THREE_MORE() {
      final List<Pose2d> subPathOnePoints = List.of(
          new Pose2d(3, 0.7, Rotation2d.fromDegrees(0)),
          new Pose2d(7, 0.7, Rotation2d.fromDegrees(0))
      );
      final Path subPathOne = new Path(true, subPathOnePoints);

      final List<Pose2d> subPathTwoPoints = List.of(
          subPathOne.getEndingPose(),
          new Pose2d(8, 0.7, Rotation2d.fromDegrees(0))
      );
      final Path subPathTwo = new Path(true, subPathTwoPoints);

      final List<Pose2d> subPathThreePoints = List.of(
          subPathTwo.getEndingPose(),
          new Pose2d(3, 2.4, Rotation2d.fromDegrees(0))
      );
      final Path subPathThree = new Path(false, subPathThreePoints);
      return new Path[]{subPathOne, subPathThree};
    }

    public static Path[] TEST_PATH() {
      final List<Pose2d> test = List.of(
          new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
          new Pose2d(1, 0, Rotation2d.fromDegrees(0))
      );
      final Path subPathOne = new Path(true, test);

      final List<Pose2d> subPathTwoPoints = List.of(
          new Pose2d(1, 0, Rotation2d.fromDegrees(0)),
          new Pose2d(0, 0, Rotation2d.fromDegrees(0))
      );
      final Path subPathTwo = new Path(false, subPathTwoPoints);

      return new Path[]{subPathOne, subPathTwo};
    }
  }
}
