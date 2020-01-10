package robot.vision.limelight.enums;

/**
 * This emun Represents Mode of Limelight's led
 *
 * @since 2020-01-10
 */
public enum LimelightLedMode {
  useCurrentPipelineMode(0),
  forceOff(1),
  forceBlink(2),
  forceOn(3);

  public final int value;

  LimelightLedMode(int value) {
    this.value = value;
  }
}
