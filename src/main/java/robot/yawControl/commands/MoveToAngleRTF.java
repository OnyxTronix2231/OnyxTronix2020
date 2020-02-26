package robot.yawControl.commands;

import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.commands.MoveTurretToAngleAndKeep;
import robot.yawControl.YawControl;

import java.util.function.DoubleSupplier;

public class MoveToAngleRTF extends MoveTurretToAngleAndKeep {

  private YawControl yawControl;
  private double initialRobotAngle;

  public MoveToAngleRTF(final YawControl yawControl, final DoubleSupplier supplierAngle) {
    super(yawControl, supplierAngle);
    this.yawControl = yawControl;
  }

  @Override
  public void initialize() {
    super.initialize();
    initialRobotAngle = yawControl.getRobotHeading();
  }

  @Override
  public void execute() {
    angle = supplierAngle.getAsDouble() - initialRobotAngle;
    System.out.println("angle = " + angle);
    super.execute();
  }
}
