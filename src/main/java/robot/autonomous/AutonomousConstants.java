package robot.autonomous;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import robot.drivetrain.Path;
import robot.drivetrain.Pose;

import java.util.List;

public final class AutonomousConstants {


  public static final double AUTONOMOUS_DISTANCE = 25;
  public static final double DRIVE_AUTONOMOUS_TIMEOUT = 5;
  public static final double SHOOTER_TIMER = 4;

  public static final class Paths {

    public static final Path getTargetToThreeBallsAtGeneratorPath = new Path(
        new Pose(new Pose2d(3.2, -2.4, Rotation2d.fromDegrees(90)), true),
        new Pose(new Pose2d(5.8, -4.4, Rotation2d.fromDegrees(-20)), true),
        new Pose(new Pose2d(5, -4.3, Rotation2d.fromDegrees(20)), false),
        new Pose(new Pose2d(5.6, -3.9, Rotation2d.fromDegrees(-20)), true),
        new Pose(new Pose2d(3.65, -3, Rotation2d.fromDegrees(120)), false));

    public static Path getTrenchToTwoBallsToOneToTwoFromGeneratorPath = new Path(
        new Pose(new Pose2d(3.1, -0.73, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(7.1, -0.73, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(8.2, -0.73, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(6.45, -2.667, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(4.1, -2.5, Rotation2d.fromDegrees(135)), true),
        new Pose(new Pose2d(3.65, -3, Rotation2d.fromDegrees(150)), true));

    public static Path getOpponentTrenchTwoBallToGeneratorThreeBallPath = new Path(
        new Pose(new Pose2d(3.1, -4.77, Rotation2d.fromDegrees(30)), true),
        new Pose(new Pose2d(6.22, -7.16, Rotation2d.fromDegrees(80)), true),
        new Pose(new Pose2d(6.3, -7.75, Rotation2d.fromDegrees(90)), true),
        new Pose(new Pose2d(4.23, -4.25, Rotation2d.fromDegrees(-160)), false),
        new Pose(new Pose2d(5.79, -4.35, Rotation2d.fromDegrees(-20)), true),
        new Pose(new Pose2d(5.24, -4.1, Rotation2d.fromDegrees(0)), false),
        new Pose(new Pose2d(5.57, -3.75, Rotation2d.fromDegrees(-20)), true));

    public static Path getTrenchThreeBallPath = new Path(
        new Pose(new Pose2d(3.1, -0.73, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(8.2, -0.73, Rotation2d.fromDegrees(0)), true));

    public static Path testPathS = new Path(
        new Pose(new Pose2d(1,-1, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(3,-2, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(5,0, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(7,-1, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(5,0, Rotation2d.fromDegrees(0)), false),
        new Pose(new Pose2d(3,-2, Rotation2d.fromDegrees(0)), false),
        new Pose(new Pose2d(1,-1, Rotation2d.fromDegrees(0)), false)
    );

    public static Path testPathNinetyDegrees = new Path(
        new Pose(new Pose2d(0,0, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(3.1,0, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(3.1,-2, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(6,-2, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(3.1,-2, Rotation2d.fromDegrees(0)), false),
        new Pose(new Pose2d(3.1,0, Rotation2d.fromDegrees(0)), false),
        new Pose(new Pose2d(0,0, Rotation2d.fromDegrees(0)), false)
    );

    public static Path testWeirdPath = new Path(
        new Pose(new Pose2d(1,-4, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(3,-6, Rotation2d.fromDegrees(90)), true),
        new Pose(new Pose2d(5,-4, Rotation2d.fromDegrees(0)), false),
        new Pose(new Pose2d(3,-6, Rotation2d.fromDegrees(0)), true),
        new Pose(new Pose2d(1,-4, Rotation2d.fromDegrees(0)), false)
    );
  }
}
