package robot.roulette.commands;

import static robot.roulette.RouletteConstants.SPIN_COLOR_SPEED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.roulette.Roulette;
import robot.roulette.RouletteColor;

public class SpinRouletteToColor extends CommandBase {

  private final Roulette roulette;
  private final RouletteColor rouletteColor;
  private final int direction;

  public SpinRouletteToColor(Roulette roulette, RouletteColor rouletteColor, int direction) {
    this.roulette = roulette;
    this.rouletteColor = rouletteColor;
    this.direction = direction;
    addRequirements(roulette);
  }

  @Override
  public void execute() {
    roulette.spinMotor(SPIN_COLOR_SPEED * direction);
  }

  @Override
  public void end(boolean interrupted) {
    roulette.stopSpin();
  }

  @Override
  public boolean isFinished() {
    return rouletteColor == roulette.getClosestColor();
  }
}
