package robot.vision.limelight.enums;

/**
 * This enum represents the different streaming modes of limelight when a USB webcam is attached.
 *
 * @since 2020-01-10
 */
public enum LimelightStreamMode {
  standard(0),
  pipMain(1),
  pipSecondary(2);

  public final int value;

  LimelightStreamMode(int value) {
    this.value = value;
  }
}
