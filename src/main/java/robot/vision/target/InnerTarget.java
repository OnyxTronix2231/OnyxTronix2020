package robot.vision.target;

import static robot.vision.VisionConstants.*;
import static robot.vision.VisionConstants.HEIGHT_OFFSET_INNER_OUTER_CENTER;

 class InnerTarget implements VisionTarget {

    private final double horizontalOffset;
    private final double verticalOffset;
    private final double orientation;
    private final double distance;
    private final double x;
    private final double y;

    InnerTarget(OuterTarget target) {
        this.x = target.getX();
        this.y = target.getY() + DISTANCE_BETWEEN_OUTER_INNER_TARGET;
        this.orientation = Math.toDegrees(Math.atan(x / y));
        this.distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        this.horizontalOffset = target.getHorizontalOffset() - 90 + orientation;
        this.verticalOffset = Math.toDegrees(Math.atan(
                TARGET_HEIGHT_CM - CAMERA_HEIGHT_CM + HEIGHT_OFFSET_INNER_OUTER_CENTER) /
                distance);
    }

    @Override
    public double getHorizontalOffset() {
        return horizontalOffset;
    }

    @Override
    public double getVerticalOffset() {
        return verticalOffset;
    }

    @Override
    public double getOrientation() {
        return orientation;
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
