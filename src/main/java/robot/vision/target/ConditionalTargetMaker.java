package robot.vision.target;

import java.util.function.BooleanSupplier;

public final class ConditionalTargetMaker {

  private final VisionTargetFactory factory;
  private final BooleanSupplier condition;

  /**
   * Makes Inner or Outer target using a given condition
   * @param factory Factory that makes targets
   * @param condition condition to make targets. If true, returns Inner. else return outer
   */
  public ConditionalTargetMaker(final VisionTargetFactory factory, final BooleanSupplier condition) {
    this.factory = factory;
    this.condition = condition;
  }

  public final VisionTarget makeTarget() {
    if(condition.getAsBoolean()) {
      return factory.makeTarget(VisionTargetType.INNER_TARGET);
    }
    return factory.makeTarget(VisionTargetType.OUTER_TARGET);
  }
}
