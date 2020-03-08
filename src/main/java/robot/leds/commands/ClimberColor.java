package robot.leds.commands;

import static robot.leds.RGBLedsConstants.NOTHING;
import static robot.leds.RGBLedsConstants.RED;
import static robot.leds.RGBLedsConstants.WAIT_BETWEEN_COLORS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.leds.RGBLeds;

public class ClimberColor extends CommandBase {

  private final RGBLeds rgbLeds;

  public ClimberColor(final RGBLeds rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void execute() {
    while (true) {
      rgbLeds.changeColor(RED);
      new WaitCommand(WAIT_BETWEEN_COLORS);
      rgbLeds.changeColor(NOTHING);
    }
  }
}
