package robot.roulette.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.roulette.Roulette;

public class SpinRouletteToColorIfExists extends ConditionalCommand {

    public SpinRouletteToColorIfExists(final Roulette roulette) {
        super(new SpinRouletteToColorPeriodically(roulette,
                        () -> roulette.getColorCountRequiredToColor(roulette.getRequiredColorFromMatchColor())),
                new InstantCommand(() -> System.out.println("Color Doesn't exist")),
                () -> roulette.getRequiredColorFromMatchColor() != null);
    }
}
