package robot.yawControl.commands;

import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.commands.MoveTurretToAngleAndKeep;

import java.util.function.DoubleSupplier;

public class MoveToAngleRTF extends MoveTurretToAngleAndKeep {

  private final DriveTrain driveTrain;
  private double initialRobotAngle;

  public MoveToAngleRTF(final Turret turret, final DriveTrain driveTrain, final DoubleSupplier supplierAngle) {
    super(turret, supplierAngle);
    this.driveTrain = driveTrain;
  }

  @Override
  public void initialize() {
    super.initialize();
    initialRobotAngle = driveTrain.getOdometryHeading();
  }

  @Override
  public void execute() {
    angle = supplierAngle.getAsDouble() - initialRobotAngle;
    super.execute();
  }
}
