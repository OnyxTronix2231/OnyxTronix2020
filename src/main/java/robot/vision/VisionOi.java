package robot.vision;

import onyxTronix.UniqueTriggerCache;
import robot.turret.Turret;
import robot.vision.commands.CorrectTurretToTarget;
import robot.vision.limelight.Limelight;

public class VisionOi {

  public VisionOi(final UniqueTriggerCache buttonsJoystickButtonCache,
                  final Turret turret) {
    turret.setDefaultCommand(new CorrectTurretToTarget(turret, () -> {
      if(Limelight.getInstance().targetFound()) {
        return new VisionTarget(Limelight.getInstance().getTarget());
      } else {
        return null;
      }
    }));

}
}
