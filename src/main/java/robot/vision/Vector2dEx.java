package robot.vision;

import edu.wpi.first.wpilibj.drive.Vector2d;

public class Vector2dEx extends Vector2d {

  public Vector2dEx() {}

  public Vector2dEx(final double x, final double y) {
    super(x, y);
  }

  public static Vector2dEx fromMagnitudeDirection(final double magnitude, final double direction) {
    return new Vector2dEx(magnitude * Math.cos(Math.toRadians(direction)),
        magnitude * Math.sin(Math.toRadians(direction)));
  }

  public void add(final Vector2d other) {
    x += other.x;
    y += other.y;
  }

  public void subtract(final Vector2d other) {
    x -= other.x;
    y -= other.y;
  }

  public double direction() {
    return Math.toDegrees(Math.atan2(y, x));
  }

  public Vector2dEx clone() {
    return new Vector2dEx(x, y);
  }
}
