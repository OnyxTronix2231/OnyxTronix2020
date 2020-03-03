package robot.leds.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;
import robot.leds.RGBLEDs;
import robot.leds.RGBLEDsConstants;

public class DriveColor extends CommandBase {

  private final RGBLEDs rgbLeds;

  public DriveColor(RGBLEDs rgbLeds) {
    this.rgbLeds = rgbLeds;
  }

  @Override
  public void execute() {
      rgbLeds.changeColor(RGBLEDsConstants.YELLOW);
  }
}
