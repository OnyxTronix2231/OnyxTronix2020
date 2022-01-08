package robot.yawControl.commands;

import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.commands.MoveTurretToAngleAndKeep;
import robot.yawControl.YawControl;

import java.util.function.DoubleSupplier;

public class MoveToAngleRTF extends MoveTurretToAngleAndKeep {

  private YawControl yawControl;

  public MoveToAngleRTF(final YawControl yawControl, final DoubleSupplier supplierAngle) {
    super(yawControl, supplierAngle);
    this.yawControl = yawControl;
  }

  @Override
  public void execute() {
    angle = supplierAngle.getAsDouble() - yawControl.getRobotHeading();
    super.execute();
  }
}
