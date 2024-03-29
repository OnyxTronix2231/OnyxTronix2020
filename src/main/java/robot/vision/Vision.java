package robot.vision;

import static robot.vision.VisionConstants.MAX_INNER_DISTANCE;
import static robot.vision.VisionConstants.MAX_INNER_ORIENTATION;
import static robot.vision.VisionConstants.MIN_INNER_DISTANCE;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.loaderConveyor.LoaderConveyor;
import robot.vision.target.TargetChooser;
import robot.vision.target.DistanceOrientationTargetChooser;
import robot.vision.target.VisionTarget;
import robot.vision.target.VisionTargetFactory;
import robot.vision.target.VisionTargetType;
import vision.limelight.Limelight;
import vision.limelight.enums.LimelightLedMode;

public class Vision extends SubsystemBase {

  private VisionTarget innerTarget;
  private VisionTarget outerTarget;
  private final VisionTargetFactory factory;
  private final TargetChooser targetChooser;

  public Vision(VisionTargetFactory factory) {
    this.factory = factory;
    targetChooser = new DistanceOrientationTargetChooser(this, MAX_INNER_DISTANCE, MIN_INNER_DISTANCE,
        MAX_INNER_ORIENTATION);
    innerTarget = factory.makeTarget(VisionTargetType.INNER_TARGET);
    outerTarget = factory.makeTarget(VisionTargetType.OUTER_TARGET);
    Shuffleboard.getTab("Vision").addNumber("Distance to chosen target", () -> getDependableTarget().getDistance());
    Shuffleboard.getTab("Vision").addNumber("Orientation Outer", () -> outerTarget.getTurretOrientation());
    Shuffleboard.getTab("Vision").addNumber("Horizontal outer", () -> outerTarget.getHorizontalOffset());
    Shuffleboard.getTab("Vision").addNumber("Horizontal inner", () -> innerTarget.getHorizontalOffset());
    Shuffleboard.getTab("Vision").addNumber("Robot X", () -> getVectorRobotToFieldZeroMeters().x);
    Shuffleboard.getTab("Vision").addNumber("Robot Y", () -> getVectorRobotToFieldZeroMeters().y);
    Limelight.getInstance().setLedMode(LimelightLedMode.forceOff);
  }

  @Override
  public void periodic() {
    if (Limelight.getInstance().targetFound()) {
      innerTarget = factory.makeTarget(VisionTargetType.INNER_TARGET);
      outerTarget = factory.makeTarget(VisionTargetType.OUTER_TARGET);
    }
  }

  public VisionTarget getDependableTarget() {
    return targetChooser.chooseTarget();
  }

  public boolean canHitOuterTarget() {
    return targetChooser.chooseTarget() == innerTarget && Limelight.getInstance().targetFound();
  }

  public VisionTarget getInnerTarget() {
    return innerTarget;
  }

  public VisionTarget getOuterTarget() {
    return outerTarget;
  }

  public void setPipeline(final int index) {
    Limelight.getInstance().setPipeline(index);
  }

  public Vector2dEx getVectorRobotToFieldZeroMeters() {
    Vector2dEx vectorToFieldZero = outerTarget.getRobotToTargetVector();
    vectorToFieldZero.subtract(VisionConstants.VECTOR_FIELD_ZERO_TO_OUTER);
    vectorToFieldZero.x /= 100;
    vectorToFieldZero.y /= 100;
    return vectorToFieldZero;
  }

  public void setLEDMode(LimelightLedMode ledMode) {
    Limelight.getInstance().setLedMode(ledMode);
  }
}
