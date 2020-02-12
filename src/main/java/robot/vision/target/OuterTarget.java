package robot.vision.target;

import robot.vision.VisionCalculations;
import vision.limelight.target.LimelightTarget;

public class OuterTarget implements VisionTarget {

    private final double horizontalOffset;
    private final double verticalOffset;
    private final double orientation;
    private final double distance;
    private final double x;
    private final double y;

    OuterTarget(LimelightTarget target, double accelerometerAngle, double turretAngle) {
        if(target != null) {
            this.horizontalOffset = target.getHorizontalOffsetToCrosshair();
            this.verticalOffset = target.getVerticalOffsetToCrosshair();
            this.orientation = VisionCalculations.calculateOrientationToTarget(accelerometerAngle, turretAngle, target);
            this.distance = VisionCalculations.calculateDistance(target);
            this.x = distance * Math.sin(Math.toRadians(orientation));
            this.y = distance * Math.cos(Math.toRadians(orientation));
        } else {
            this.horizontalOffset = 0;
            this.verticalOffset = 0;
            this.orientation = 0;
            this.distance = 0;
            this.x = 0;
            this.y = 0;
        }
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
