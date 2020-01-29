package robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class RotateToAngleNavX extends CommandBase {

  private final DriveTrain driveTrain;
  private final DoubleSupplier angleSupplier;

  public RotateToAngleNavX(final DriveTrain driveTrain, final DoubleSupplier angleSupplier) {
    this.driveTrain = driveTrain;
    this.angleSupplier = angleSupplier;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
    driveTrain.resetGyroPID();
    driveTrain.setGyroPIDSetPoint(angleSupplier.getAsDouble());
  }

  @Override
  public void execute() {
    driveTrain.arcadeDrive(0, driveTrain.calculateGyroPIDProduct());
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return driveTrain.isGyroPIDatSetPoint();
  }
}
