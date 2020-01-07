package robot.shooter;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.shooter.commands.ShootBySpeed;

public class ShooterOi {
    public ShooterOi (UniqueTriggerCache buttonsTriggerCache, ShootBySpeed shootBySpeed){
        Trigger shootBySpeedButton = buttonsTriggerCache.createJoystickTrigger(1);
        shootBySpeedButton.whileActiveContinuous(shootBySpeed);
    }
}
