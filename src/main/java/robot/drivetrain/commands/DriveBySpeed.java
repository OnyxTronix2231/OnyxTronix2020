package robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveBySpeed extends CommandBase {

  private final DriveTrain driveTrain;
  private final DoubleSupplier forwardSpeedCalculationSupplier;
  private final DoubleSupplier rotationSpeedCalculationSupplier;

  public DriveBySpeed(final DriveTrain driveTrain, final DoubleSupplier forwardSpeedCalculation,
                      final DoubleSupplier rotationSpeedCalculation) {
    this.driveTrain = driveTrain;
    this.forwardSpeedCalculationSupplier = forwardSpeedCalculation;
    this.rotationSpeedCalculationSupplier = rotationSpeedCalculation;
    addRequirements(driveTrain);
  }

  @Override
  public void execute() {
    driveTrain.arcadeDrive(forwardSpeedCalculationSupplier.getAsDouble(),
        rotationSpeedCalculationSupplier.getAsDouble());
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(final boolean interrupted) {
    driveTrain.stopDrive();
  }
}
