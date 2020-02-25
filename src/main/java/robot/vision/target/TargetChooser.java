package robot.vision.target;

import java.util.function.BooleanSupplier;

public class TargetChooser {

  private final VisionTargetSupplier trueTargetSupplier;
  private final VisionTargetSupplier falseTargetSupplier;
  protected final BooleanSupplier condition;

  public TargetChooser(final VisionTargetSupplier trueTargetSupplier,
                       final VisionTargetSupplier falseTargetSupplier, final BooleanSupplier condition) {
    this.condition = condition;
    this.trueTargetSupplier = trueTargetSupplier;
    this.falseTargetSupplier = falseTargetSupplier;
  }

  public final VisionTarget chooseTarget() {
    if (condition.getAsBoolean()) {
      return trueTargetSupplier.getAsVisionTarget();
    }
    return falseTargetSupplier.getAsVisionTarget();
  }
}
