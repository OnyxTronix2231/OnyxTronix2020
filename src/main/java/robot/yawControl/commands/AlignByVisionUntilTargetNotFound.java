package robot.yawControl.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.turret.Turret;
import robot.turret.commands.MoveTurretByAngleAndKeep;
import robot.turret.commands.MoveTurretToAngle;
import robot.yawControl.YawControl;
import vision.limelight.Limelight;

import java.util.function.DoubleSupplier;

public class AlignByVisionUntilTargetNotFound extends SequentialCommandGroup {
  YawControl yawControl;
  public AlignByVisionUntilTargetNotFound(YawControl yawControl, DoubleSupplier angleSupplier) {
    super(new MoveTurretByAngleAndKeep(yawControl, angleSupplier).withInterrupt(() -> !Limelight.getInstance().targetFound()),
        new SetTurretState(yawControl, YawControl.TurretState.RTF), new WaitUntilCommand(() -> Limelight.getInstance().targetFound()));
    this.yawControl =yawControl;
  }

  @Override
  public boolean isFinished() {
    if(super.isFinished()) {
      super.initialize();
    }
    return false;
  }

}
