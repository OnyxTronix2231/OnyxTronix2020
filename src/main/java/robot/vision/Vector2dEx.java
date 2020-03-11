package robot.vision;

import edu.wpi.first.wpilibj.drive.Vector2d;

/***
 * Extension class for Vector2d
 * Adds adding, subtracting, cloning, direction and the ability to generate a vector from magnitude and direction
 *
 * @author Shaked Cohen
 */
public class Vector2dEx extends Vector2d {

  public Vector2dEx() {}

  public Vector2dEx(final double x, final double y) {
    super(x, y);
  }

  /**
   * Generates a vector from a given magnitude and direction
   * @param magnitude magnitude of generated vector
   * @param direction direction of generated vector in degrees
   * @return generated vector
   */
  public static Vector2dEx fromMagnitudeDirection(final double magnitude, final double direction) {
    return new Vector2dEx(magnitude * Math.cos(Math.toRadians(direction)),
        magnitude * Math.sin(Math.toRadians(direction)));
  }

  /**
   * Add other vector to this vector
   * @param other vector to add to this vector
   */
  public void add(final Vector2d other) {
    x += other.x;
    y += other.y;
  }

  /**
   * Subtract other vector from this vector
   * @param other vector to subtract from this vector
   */
  public void subtract(final Vector2d other) {
    x -= other.x;
    y -= other.y;
  }

  /**
   * Calculate and return the direction of this vector
   * @return direction of vector
   */
  public double direction() {
    return Math.toDegrees(Math.atan2(y, x));
  }

  /**
   * clone this Vector
   * @return a copy of the instance being cloned
   */
  public Vector2dEx clone() {
    return new Vector2dEx(x, y);
  }
}
