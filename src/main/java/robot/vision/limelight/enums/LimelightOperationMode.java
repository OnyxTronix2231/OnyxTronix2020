package robot.vision.limelight.enums;

/**
 * This enum represents the operation modes of limelight
 *
 * @since 2020-01-10
 */
public enum LimelightOperationMode {
  visionProcessor(0),
  driverCamera(1);

  public final int value;

  LimelightOperationMode(int value) {
    this.value = value;
  }
}
