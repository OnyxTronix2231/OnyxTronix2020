package robot.turret;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OffsetChecker extends SubsystemBase {

  private double offset;

  public OffsetChecker(final double offset) {
    this.offset = offset;
  }

  public void incrementOffset() {
    ++offset;
  }

  public void decrementOffset() {
   --offset;
  }

  public double getOffset() {
    return offset;
  }
}
