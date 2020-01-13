package robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveByDistance extends CommandBase {

  private final DriveTrain driveTrain;
  private final DoubleSupplier distanceSupplier;
  public DriveByDistance(final DriveTrain driveTrain, final DoubleSupplier distanceSupplier) {
    this.driveTrain = driveTrain;
    this.distanceSupplier = distanceSupplier;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
    driveTrain.driveByMotionMagic(distanceSupplier.getAsDouble());
  }

  @Override
  public boolean isFinished() {
    return driveTrain.isDriveOnTarget();
  }

  @Override
  public void end(final boolean interrupted) {
    driveTrain.stopDrive();
  }
}
