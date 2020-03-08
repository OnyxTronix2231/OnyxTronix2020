package robot.roulette;

import static robot.roulette.RouletteConstants.SPIN_ROTATIONS_SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueButtonCache;
import robot.roulette.commands.CloseRoulettePistons;
import robot.roulette.commands.SpinRouletteByColorCount;
import robot.roulette.commands.SpinRouletteToAverageRotations;
import robot.roulette.commands.SpinRouletteToColorIfExists;

public class RouletteOiBinder {

  public RouletteOiBinder(final Roulette roulette, final Trigger togglePistons, final Trigger spinRouletteByColorExist,
                          final Trigger spinRouletteByColorCount) {
    togglePistons.whenActive(new CloseRoulettePistons(roulette));

    spinRouletteByColorExist.whenActive(new SpinRouletteToColorIfExists(roulette));

    spinRouletteByColorCount.whenActive(new SpinRouletteToAverageRotations(roulette));
  }
}
