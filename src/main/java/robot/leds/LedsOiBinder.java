package robot.leds;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import robot.leds.commands.BallCollectorColor;
import robot.leds.commands.ClimberColor;
import robot.leds.commands.DriveColor;
import robot.leds.commands.ShooterColor;
import robot.shooter.Shooter;

public class LedsOiBinder {

  public LedsOiBinder(final Shooter shooter, final RGBLeds rgbLeds, final JoystickAxis forwardAxis, final JoystickAxis rotateAxis,
                      final Trigger spinShooterAndAlignToTarget, final Trigger collectAndLoadBall,
                      final Trigger climb) {
    forwardAxis.whileActiveContinuous(new DriveColor(rgbLeds));

    rotateAxis.whileActiveContinuous(new DriveColor(rgbLeds));

    spinShooterAndAlignToTarget.whileActiveContinuous(new ShooterColor(shooter, rgbLeds));

    collectAndLoadBall.whileActiveContinuous(new BallCollectorColor(rgbLeds));

    climb.whileActiveContinuous(new ClimberColor(rgbLeds));
  }
}
