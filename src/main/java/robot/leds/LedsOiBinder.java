package robot.leds;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import robot.leds.commands.BallCollectorColor;
import robot.leds.commands.ClimberColor;
import robot.leds.commands.DriveColor;
import robot.leds.commands.ShooterColor;
import robot.shooter.Shooter;

public class LedsOiBinder {

  public LedsOiBinder(Shooter shooter, RGBLEDs rgbLeds, final JoystickAxis forwardAxis, final JoystickAxis rotateAxis,
                      final Trigger spinShooterAndAlignToTarget, final Trigger collectAndLoadBall,
                      final Trigger climb) {
    forwardAxis.whenActive(new DriveColor(rgbLeds));

    rotateAxis.whenActive(new DriveColor(rgbLeds));

    spinShooterAndAlignToTarget.whenActive(new ShooterColor(shooter, rgbLeds));

    collectAndLoadBall.whenActive(new BallCollectorColor(rgbLeds));

    climb.whenActive(new ClimberColor(rgbLeds));
  }
}
