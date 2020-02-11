package robot.vision.limelight.target;

/**
 * Represents a basic vision Target retrieved From Limelight
 *
 * @since 2020-01-10
 */
public class LimelightTarget {

  private final double horizontalOffsetToCrosshair;
  private final double verticalOffsetToCrosshair;
  private final double targetArea;
  private final double skew;
  private final double shortSideOfFittedBoundingBox;
  private final double longSideOfFittedBoundingBox;
  private final double horizontalSideOfRoughBoundingBox;
  private final double verticalSideOfRoughBoundingBox;

  /**
   * Default Constructor
   *
   * @param horizontalOffsetToCrosshair Limelight1: -27 to 27 deg, Limelight2: -29.8 to 29.8 deg.
   * @param verticalOffsetToCrosshair Limelight1: -20.5 to 20.5 deg, Limelight2: -24.85 to 24.85 deg.
   * @param targetArea Percentage of target Area From Image area (0 - 100).
   * @param skew Skew or Rotation(-90 to 0 deg)
   * @param shortSideOfFittedBoundingBox Sidelength of shortest side of the fitted bounding box (pixels)
   * @param longSideOfFittedBoundingBox Sidelength of longest side of the fitted bounding box (pixels)
   * @param horizontalSideOfRoughBoundingBox Horizontal sidelength of the rough bounding box (0 - 320 pixels)
   * @param verticalSideOfRoughBoundingBox 	Vertical sidelength of the rough bounding box (0 - 320 pixels)
   */
  public LimelightTarget(final double horizontalOffsetToCrosshair, final double verticalOffsetToCrosshair,
                         final double targetArea, final double skew,
                         final double shortSideOfFittedBoundingBox, final double longSideOfFittedBoundingBox,
                         final double horizontalSideOfRoughBoundingBox, final double verticalSideOfRoughBoundingBox) {
    this.horizontalOffsetToCrosshair = horizontalOffsetToCrosshair;
    this.verticalOffsetToCrosshair = verticalOffsetToCrosshair;
    this.targetArea = targetArea;
    this.skew = skew;
    this.shortSideOfFittedBoundingBox = shortSideOfFittedBoundingBox;
    this.longSideOfFittedBoundingBox = longSideOfFittedBoundingBox;
    this.horizontalSideOfRoughBoundingBox = horizontalSideOfRoughBoundingBox;
    this.verticalSideOfRoughBoundingBox = verticalSideOfRoughBoundingBox;
  }

  /**
   * Copy constructor
   * @param limelightTarget target to be copied
   */
  public LimelightTarget(final LimelightTarget limelightTarget) {
    this.horizontalOffsetToCrosshair = limelightTarget.horizontalOffsetToCrosshair;
    this.verticalOffsetToCrosshair = limelightTarget.verticalOffsetToCrosshair;
    this.targetArea = limelightTarget.targetArea;
    this.skew = limelightTarget.skew;
    this.shortSideOfFittedBoundingBox = limelightTarget.shortSideOfFittedBoundingBox;
    this.longSideOfFittedBoundingBox = limelightTarget.longSideOfFittedBoundingBox;
    this.horizontalSideOfRoughBoundingBox = limelightTarget.horizontalSideOfRoughBoundingBox;
    this.verticalSideOfRoughBoundingBox = limelightTarget.verticalSideOfRoughBoundingBox;
  }

  /**
   * Returns the horizontal offset to the crosshair
   * @return Horizontal offset to the crosshair
   */
  public double getHorizontalOffsetToCrosshair() {
    return horizontalOffsetToCrosshair;
  }

  /**
   * Returns the vertical offset to the crosshair
   * @return Vertical offset to the crosshair
   */
  public double getVerticalOffsetToCrosshair() {
    return verticalOffsetToCrosshair;
  }

  /**
   * Returns the percentage of the target area from the image area
   * @return The percentage of the target area from the image area
   */
  public double getTargetArea() {
    return targetArea;
  }

  /**
   * Returns the skew or rotation of the target(0-90 deg)
   * @return Skew or rotation of the target(0-90 deg)
   */
  public double getSkew() {
    return skew;
  }

  /**
   * Returns the length of the short side of the fitted bounding box in pixels.
   * @return Length of the short side of the fitted bounding box in pixels.
   */
  public double getShortSideOfFittedBoundingBox() {
    return shortSideOfFittedBoundingBox;
  }

  /**
   * Returns the length of the long side of fitted bounding box in pixels.
   * @return Length of the long side of fitted bounding box in pixels.
   */
  public double getLongSideOfFittedBoundingBox() {
    return longSideOfFittedBoundingBox;
  }

  /**
   * Returns the horizontal length of rough bounding box in pixels
   * @return Horizontal length of rough bounding box in pixels
   */
  public double getHorizontalSideOfRoughBoundingBox() {
    return horizontalSideOfRoughBoundingBox;
  }

  /**
   * Returns the vertical length of rough bounding box in pixels.
   * @return Vertical length of rough bounding box in pixels.
   */
  public double getVerticalSideOfRoughBoundingBox() {
    return verticalSideOfRoughBoundingBox;
  }
}
