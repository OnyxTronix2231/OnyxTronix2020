package robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveBySpeed extends CommandBase {

  private final DriveTrain driveTrain;
  private final DoubleSupplier forwardSpeedCalculationSupplier;
  private final DoubleSupplier rotationSpeedCalculationSupplier;

  public DriveBySpeed(final DriveTrain driveTrain, final DoubleSupplier forwardSpeedCalculationSupplier,
                      final DoubleSupplier rotationSpeedCalculationSupplier) {
    this.driveTrain = driveTrain;
    this.forwardSpeedCalculationSupplier = forwardSpeedCalculationSupplier;
    this.rotationSpeedCalculationSupplier = rotationSpeedCalculationSupplier;
    addRequirements(driveTrain);
  }

  @Override
  public void execute() {
    driveTrain.arcadeDrive(forwardSpeedCalculationSupplier.getAsDouble(),
        rotationSpeedCalculationSupplier.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    driveTrain.stopDrive();
  }
}
