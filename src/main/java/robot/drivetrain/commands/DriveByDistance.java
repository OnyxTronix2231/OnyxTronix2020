package robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveByDistance extends CommandBase {

  private final DriveTrain driveTrain;
  private final DoubleSupplier distance;
  private double leftTarget;
  private double rightTarget;

  public DriveByDistance(final DriveTrain driveTrain, final DoubleSupplier distance) {
    this.driveTrain = driveTrain;
    this.distance = distance;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
    leftTarget = driveTrain.getLeftTargetFromDistance(distance.getAsDouble());
    rightTarget = driveTrain.getRightTargetFromDistance(distance.getAsDouble());
  }

  @Override
  public void execute() {
    driveTrain.driveByMotionMagic(leftTarget, rightTarget);
  }

  @Override
  public boolean isFinished() {
    return driveTrain.isDriveOnTarget(leftTarget, rightTarget);
  }

  @Override
  public void end(final boolean interrupted) {
    driveTrain.stopDrive();
  }
}
