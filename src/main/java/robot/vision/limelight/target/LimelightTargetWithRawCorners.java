package robot.vision.limelight.target;

/**
 * Represents a vision target with raw corners. Inherits LimelightTarget
 *
 * @since 2020-01-10
 */
public class LimelightTargetWithRawCorners extends LimelightTarget implements IRawCornersInterface {

  private final Corner[] corners;

  public LimelightTargetWithRawCorners(final double horizontalOffsetToCrosshair, final double verticalOffsetToCrosshair,
                                       final double targetArea, final double skew,
                                       final double shortSideOfFittedBoundingBox, final double longSideOfFittedBoundingBox,
                                       final double horizontalSideOfRoughBoundingBox, final double verticalSideOfRoughBoundingBox,
                                       Corner[] corners) {
    super(horizontalOffsetToCrosshair, verticalOffsetToCrosshair, targetArea, skew, shortSideOfFittedBoundingBox, longSideOfFittedBoundingBox, horizontalSideOfRoughBoundingBox, verticalSideOfRoughBoundingBox);
    this.corners = corners;
  }

  /**
   * Default Constructor
   *
   * @param limelightTarget target to be copied to create the  members of super.
   * @param corners Array of the raw corners of the target.
   */
  public LimelightTargetWithRawCorners(LimelightTarget limelightTarget, Corner[] corners) {
    super(limelightTarget);
    this.corners = corners;
  }

  public LimelightTargetWithRawCorners(LimelightTargetWithRawCorners limelightTargetWithRawCorners) {
    super(limelightTargetWithRawCorners);
    corners = limelightTargetWithRawCorners.getRawCorners();

  }

  @Override
  public Corner[] getRawCorners() {
    Corner[] copyOfCorners = new Corner[corners.length];
    System.arraycopy(corners, 0, copyOfCorners, 0, corners.length);
    return copyOfCorners;
  }
}
