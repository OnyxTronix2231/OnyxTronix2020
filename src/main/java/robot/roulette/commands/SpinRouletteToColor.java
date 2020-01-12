package robot.roulette.commands;

import static robot.roulette.RouletteConstants.SPIN_COLOR_SPEED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.Roulette;
import robot.roulette.RouletteColor;

public class SpinRouletteToColor extends CommandBase {

  private final Roulette roulette;
  private RouletteColor requiredColor;

  public SpinRouletteToColor(final Roulette roulette) {
    this.roulette = roulette;
    addRequirements(roulette);
  }

  @Override
  public void initialize() {

    requiredColor = roulette.getRequiredMatchColor();
  }

  @Override
  public void execute() {
    roulette.spinMotor(SPIN_COLOR_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    roulette.stopSpin();
  }

  @Override
  public boolean isFinished() {
    return requiredColor == roulette.getClosestColor();
  }
}
