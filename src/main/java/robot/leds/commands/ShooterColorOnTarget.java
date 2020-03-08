package robot.leds.commands;

import static robot.leds.RGBLedsConstants.GREEN;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import robot.leds.RGBLeds;

public class ShooterColorOnTarget extends InstantCommand {

  private final RGBLeds rgbLeds;

  public ShooterColorOnTarget(RGBLeds rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void initialize() {
    rgbLeds.changeColor(GREEN);
  }
}
