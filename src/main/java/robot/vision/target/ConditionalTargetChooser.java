package robot.vision.target;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import java.util.function.BooleanSupplier;

public class ConditionalTargetChooser {

  private final VisionTargetSupplier trueTargetSupplier;
  private final VisionTargetSupplier falseTargetSupplier;
  private final BooleanSupplier condition;

  public ConditionalTargetChooser(final VisionTargetSupplier trueTargetSupplier,
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
