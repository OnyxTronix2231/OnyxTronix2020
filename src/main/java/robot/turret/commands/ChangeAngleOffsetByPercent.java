package robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class ChangeAngleOffsetByPercent extends CommandBase {

  private final Turret turret;
  private final DoubleSupplier percentSupplier;

  public ChangeAngleOffsetByPercent(final Turret turret, final DoubleSupplier percentSupplier) {
    this.turret = turret;
    this.percentSupplier = percentSupplier;
  }

  @Override
  public void execute() {
    turret.setOffset(percentSupplier.getAsDouble());
  }
}
