package robot.vision.limelight.target;

/**
 * Represents a raw corner of A Vision Target
 *
 * @since 2020-01-10
 */
public class Corner {
  private final double x;
  private final double y;

  /**
   * Default Constructor
   * @param x Represents the X coordinate of the corner.
   * @param y Represents thr Y coordinate of the corner.
   */
  public Corner(final double x, final double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the X coordinate of the corner.
   * @return X coordinate of the corner.
   */
  public double getX() {
    return x;
  }

  /**
   * Returns the Y coordinate of the corner.
   * @return Y coordinate of the corner.
   */
  public double getY() {
    return y;
  }
}
