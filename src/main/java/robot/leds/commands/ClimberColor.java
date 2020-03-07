package robot.leds.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.leds.RGBLeds;

public class ClimberColor extends CommandBase {

  private final RGBLeds rgbLeds;

  public ClimberColor(RGBLeds rgbLeds) {
    this.rgbLeds = rgbLeds;
    addRequirements(rgbLeds);
  }

  @Override
  public void execute() {
    while (true) {
      rgbLeds.changeColor('T');
      new WaitCommand(0.1);
      rgbLeds.changeColor('N');
    }
  }
}
