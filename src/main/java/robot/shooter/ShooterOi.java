package robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import robot.shooter.commands.ShootBySpeed;

public class ShooterOi {
    public ShooterOi(final XboxController controller, final ShootBySpeed shootBySpeed) {
        final JoystickButton button = new JoystickButton(controller, 1);
        button.whileActiveContinuous(shootBySpeed);
    }
}
