package robot.leds.commands;

import static robot.leds.RGBLedsConstants.*;
import static robot.leds.RGBLedsConstants.RED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.leds.RGBLeds;
import robot.leds.RGBLedsConstants;

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
      new WaitCommand(0.1);
      rgbLeds.changeColor(NOTHING);
    }
  }
}
