package robot.vision;

import static robot.vision.VisionConstants.MAX_INNER_DISTANCE;
import static robot.vision.VisionConstants.MAX_INNER_ORIENTATION;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.vision.target.ConditionalTargetChooser;
import robot.vision.target.DistanceOrientationConditionalChooser;
import robot.vision.target.VisionTarget;
import robot.vision.target.VisionTargetFactory;
import robot.vision.target.VisionTargetType;
import vision.limelight.Limelight;
import vision.limelight.enums.LimelightLedMode;

public class Vision extends SubsystemBase {

  private VisionTarget innerTarget;
  private VisionTarget outerTarget;
  private final VisionTargetFactory factory;
  private final ConditionalTargetChooser targetMaker;

  public Vision(VisionTargetFactory factory) {
    this.factory = factory;
    targetMaker = new DistanceOrientationConditionalChooser(this, MAX_INNER_DISTANCE, MAX_INNER_ORIENTATION);
    innerTarget = factory.makeTarget(VisionTargetType.INNER_TARGET);
    outerTarget = factory.makeTarget(VisionTargetType.OUTER_TARGET);
    Shuffleboard.getTab("Vision").addNumber("Outer Target Distance", () ->
        outerTarget.getDistance());
    Shuffleboard.getTab("Vision").addNumber("Orientation", () ->
        outerTarget.getOrientation());
    Limelight.getInstance().setLedMode(LimelightLedMode.useCurrentPipelineMode);
  }

  @Override
  public void periodic() {
    if (Limelight.getInstance().targetFound()) {
      innerTarget = factory.makeTarget(VisionTargetType.INNER_TARGET);
      outerTarget = factory.makeTarget(VisionTargetType.OUTER_TARGET);
    }
  }

  public VisionTarget getDependableTarget() {
    return targetMaker.chooseTarget();
  }

  public VisionTarget getInnerTarget() {
    return innerTarget;
  }

  public VisionTarget getOuterTarget() {
    return outerTarget;
  }

  public void setLedMode(final LimelightLedMode ledMode) {
    Limelight.getInstance().setLedMode(ledMode);
  }

  public void setPipeline(final int index) {
    Limelight.getInstance().setPipeline(index);
  }

  public void setLEDMode(LimelightLedMode ledMode) {
    Limelight.getInstance().setLedMode(ledMode);
  }
}
