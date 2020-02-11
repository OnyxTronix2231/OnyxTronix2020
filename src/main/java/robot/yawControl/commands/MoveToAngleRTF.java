package robot.yawControl.commands;

import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.commands.MoveToAngle;
import robot.turret.commands.MoveToAngleAndKeep;
import robot.yawControl.YawControl;

import java.util.function.DoubleSupplier;

public class MoveToAngleRTF extends MoveToAngleAndKeep {

  private DriveTrain driveTrain;
  private double initialRobotAngle;
  public MoveToAngleRTF(Turret turret,DriveTrain driveTrain, DoubleSupplier supplierAngle) {
    super(turret, supplierAngle);
    this.driveTrain = driveTrain;
  }

  @Override
  public void initialize() {
    super.initialize();
    initialRobotAngle = driveTrain.getNavXYaw();
  }

  @Override
  public void execute() {
    angle = supplierAngle.getAsDouble() - initialRobotAngle;
    super.execute();
  }
}
