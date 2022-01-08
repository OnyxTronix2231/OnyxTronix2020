package robot.yawControl;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import robot.turret.commands.MoveTurretToAngle;
import robot.vision.target.VisionTargetSupplier;
import robot.yawControl.commands.AlignByOrientationAndThenVision;
import robot.yawControl.commands.SetTurretState;

public class YawControlOiBinder {
  public YawControlOiBinder(final YawControl yawControl, final VisionTargetSupplier targetSupplier,
                            final Trigger setStateRTF, final Trigger setStateRTR,
                            final Trigger setStateHoming,
                            final Trigger alignToTarget,
                            final Trigger moveTurretToHome) {
    alignToTarget.whenActive(new AlignByOrientationAndThenVision(yawControl, targetSupplier));

    setStateRTF.whenActive(new SetTurretState(yawControl, YawControl.TurretState.RTF));

    setStateRTR.whenActive(new SetTurretState(yawControl, YawControl.TurretState.RTR));

    setStateHoming.whenActive(new SetTurretState(yawControl, YawControl.TurretState.HOMING));

    moveTurretToHome.whenActive(new MoveTurretToAngle(yawControl, () -> 0));
  }
}
