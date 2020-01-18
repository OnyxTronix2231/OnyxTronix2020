package robot.yawControl.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.yawControl.YawControl;

import java.util.function.DoubleSupplier;

public class ChangeAngleOffsetByPercent extends CommandBase {

  private final YawControl yawControl;
  private final DoubleSupplier percentSupplier;

  public ChangeAngleOffsetByPercent(YawControl yawControl, DoubleSupplier percentSupplier) {
    this.yawControl = yawControl;
    this.percentSupplier = percentSupplier;
  }

  @Override
  public void execute() {
    yawControl.setOffsetByPercent(percentSupplier.getAsDouble());
  }
}
