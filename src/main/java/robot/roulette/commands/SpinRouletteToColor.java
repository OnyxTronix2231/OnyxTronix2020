package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.Roulette;

import java.util.function.DoubleSupplier;

public class SpinRouletteToColor extends CommandBase {

  protected final Roulette roulette;
  protected final DoubleSupplier colorsRequiredSupplier;
  protected double colorsRequired;

  public SpinRouletteToColor(final Roulette roulette, DoubleSupplier colorsRequiredSupplier) {
    this.roulette = roulette;
    this.colorsRequiredSupplier = colorsRequiredSupplier;
    addRequirements(roulette);
  }

  @Override
  public void initialize() {
    colorsRequired = colorsRequiredSupplier.getAsDouble();
    roulette.resetEncoder();
  }

  @Override
  public void execute() {
    roulette.spinByColorsCount(colorsRequired);
  }

  @Override
  public boolean isFinished() {
    return roulette.isOnTargetColor(colorsRequired);
  }

  @Override
  public void end(final boolean interrupted) {
    roulette.stopMotor();
  }
}
