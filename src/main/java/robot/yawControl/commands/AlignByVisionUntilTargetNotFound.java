package robot.yawControl.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import robot.turret.Turret;
import robot.turret.commands.MoveTurretByAngleAndKeep;
import robot.turret.commands.MoveTurretToAngle;
import robot.yawControl.YawControl;
import vision.limelight.Limelight;

import java.util.function.DoubleSupplier;

public class AlignByVisionUntilTargetNotFound extends MoveTurretByAngleAndKeep {

  public AlignByVisionUntilTargetNotFound(YawControl yawControl, DoubleSupplier angleSupplier) {
    super(yawControl, angleSupplier);
  }

  @Override
  public boolean isFinished() {
    return Limelight.getInstance().targetFound();
  }
}
