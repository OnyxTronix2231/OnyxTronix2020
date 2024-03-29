package robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

import java.util.List;

public final class DriveTrainConstants {

  public static final int DRIVE_BY_DISTANCE_SLOT = 0;
  static final double ARCADE_DRIVE_ROTATION_SENSITIVITY = 0.6;
  static final double ARCADE_DRIVE_FORWARD_SENSITIVITY = 0.8;
  static final int VELOCITY_CONTROLLER_PID_SLOT = 2;
  static final int CM_TO_METERS = 100;
  static final int SEC_TO_100MS = 10;
  static final double CONVERSION_RATE = 9.5;
  static final double TOLERANCE = 3; // TODO: tuning is required
  static final double ARB_FEED_FORWARD = 0.04; // TODO: tuning is required
  private static final double INCH_TO_CM = 2.54;
  static final double PERIMETER = 6 * INCH_TO_CM * Math.PI; //TODO: tuning is required
  static final double PERIMETER_IN_METERS = PERIMETER / 100;
  private static final double ENCODER_UNITS = 2046;

  public static final class DriveTrainComponentsA {

    static final int LEFT_MASTER_PORT = 2;
    static final int LEFT_SLAVE_PORT = 3;
    static final int RIGHT_MASTER_PORT = 0;
    static final int RIGHT_SLAVE_PORT = 1;
    static final int MAX_ACCELERATION = 1000; // TODO: Calibration with A
    static final int MAX_VELOCITY = 13400; // TODO: Calibration with A
    static final int PIGEON_PORT = 0;
    static final double MAX_CLOSED_LOOP_OUTPUT = 1023;
    static final double DRIVE_BY_DISTANCE_P = 0; // TODO: Calibration with A
    static final double DRIVE_BY_DISTANCE_I = 0; // TODO: Calibration with A
    static final double DRIVE_BY_DISTANCE_D = 0; // TODO: Calibration with A
    static final double CURRENT_LIMIT = 40; // TODO: check value
    static final double TRIGGER_THRESHOLD_CURRENT = 40; // TODO: check value
    static final double TRIGGER_THRESHOLD_TIME = 0.03; // TODO: check value
    static final double PERCENTAGE_CLOSED_LOOP_OUTPUT = 1.0;
    static final double OPEN_LOOP_RAMP = 0; // TODO: Calibration with A
    static final double CLOSED_LOOP_RAMP = 0;
    static final double VELOCITY_CONTROLLER_P = 0; // TODO: Calibration with A
    static final double VELOCITY_CONTROLLER_I = 0; // TODO: Calibration with A
    static final double VELOCITY_CONTROLLER_D = 0; // TODO: Calibration with A
    static final double MAX_OUTPUT_FORWARD = 0.6;
    static final double MAX_OUTPUT_REVERSE = -0.6;
    static final double TRAJECTORY_P = 0.4; // TODO: Calibration with A
    static final double TRAJECTORY_I = 0; // TODO: Calibration with A
    static final double TRAJECTORY_D = 4; // TODO: Calibration with A

    public static final class TrajectoryParams {

      public static final double RAMSETE_B = 2;
      public static final double RAMSETE_ZETA = 0.7;
      static final int TRAJECTORY_PID_SLOT = 1;
      static final int DEGREES_IN_FULL_ROTATION = 360;
      static final double ENCODER_CPR = ENCODER_UNITS * 9.5; // TODO: Calibration with A
      static final double VOLTS = 0.365; // TODO: Calibration with A
      static final double VOLT_SECONDS_PER_METER = 0;
      static final double VOLT_SECONDS_SQUARED_PER_METER = 0.333; // TODO: Calibration with A
      static final double MAX_VOLTAGE = 10; // TODO: Calibration with A
      static final double MAX_SPEED_METERS_PER_SECOND = 3; // TODO: Calibration with A
      static final double MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 3; // TODO: Calibration with A
      static final double TRACKWIDTH_METERS = 0.679; // TODO: Calibration with A
    }

  }

  static final class Paths {

    private static final List<Pose2d> PATH_1 = List.of(
        new Pose2d(4.55, 4.55, Rotation2d.fromDegrees(8.70579)),
        new Pose2d(5.8, 4.4, Rotation2d.fromDegrees(-49.3987)),
        new Pose2d(5.59, 3.93, Rotation2d.fromDegrees(162.64597)),
        new Pose2d(3, 2.7, Rotation2d.fromDegrees(180))
    );
    private static final List<Pose2d> PATH_2 = List.of(
        new Pose2d(7.3, 0.7, Rotation2d.fromDegrees(0)),
        new Pose2d(7.3, 0.7, Rotation2d.fromDegrees(180)),
        new Pose2d(3.2, 2.4, Rotation2d.fromDegrees(180))
    );
    private static final List<Pose2d> PATH_3 = List.of(
        new Pose2d(3.2, 0.7, Rotation2d.fromDegrees(0)),
        new Pose2d(7.3, 0.7, Rotation2d.fromDegrees(0)),
        new Pose2d(7.3, 0.7, Rotation2d.fromDegrees(180)),
        new Pose2d(3.2, 0.7, Rotation2d.fromDegrees(180))
    );
    private static final List<Pose2d> PATH_4 = List.of(
        new Pose2d(6.3, 7.5, Rotation2d.fromDegrees(0)),
        new Pose2d(6.3, 7.5, Rotation2d.fromDegrees(-140)),
        new Pose2d(3.1, 5.1, Rotation2d.fromDegrees(-140))
    );
    private static final List<Pose2d> PATH_5 = List.of(
        new Pose2d(3.2, 3.7, Rotation2d.fromDegrees(0)),
        new Pose2d(9.8, 3.7, Rotation2d.fromDegrees(0)),
        new Pose2d(9.8, 3.7, Rotation2d.fromDegrees(0)),
        new Pose2d(10.2, 4.5, Rotation2d.fromDegrees(45))
    );
    static final List<List<Pose2d>> PATHS = List.of(PATH_1, PATH_2, PATH_3, PATH_4, PATH_5);

    static Path[] PrimaryPath() {
      final Pose2d startingPose = new Pose2d(1, 1, Rotation2d.fromDegrees(0));

      final List<Pose2d> pathOnePoints = List.of(
          new Pose2d(),
          new Pose2d(),
          new Pose2d()
      );
      final Path pathOne = new Path(true, pathOnePoints);

      final List<Pose2d> pathTwoPoints = List.of(
          new Pose2d(),
          new Pose2d()
      );
      final Path pathTwo = new Path(false, pathTwoPoints);

      final Path[] paths = {pathOne, pathTwo};
      return paths;
    }

    private static Pose2d[] startingPoses() {
      final Pose2d[] startingPoses = new Pose2d[5];
      startingPoses[0] = new Pose2d(3.1, 3.5, Rotation2d.fromDegrees(37.5686));
      startingPoses[1] = new Pose2d(3.2, 0.7, Rotation2d.fromDegrees(0));
      startingPoses[2] = new Pose2d(3.2, 0.7, Rotation2d.fromDegrees(180));
      startingPoses[3] = new Pose2d(3.2, 7.5, Rotation2d.fromDegrees(0));
      startingPoses[4] = new Pose2d(3.2, 3.7, Rotation2d.fromDegrees(-140));
      return startingPoses;
    }
  }
}
