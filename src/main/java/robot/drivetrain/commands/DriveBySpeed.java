package robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveBySpeed extends CommandBase {

  private final DriveTrain driveTrain;
  private final DoubleSupplier forwardSpeedCalculation;
  private final DoubleSupplier rotationSpeedCalculation;

  public DriveBySpeed(final DriveTrain driveTrain, final DoubleSupplier forwardSpeedCalculation,
                      final DoubleSupplier rotationSpeedCalculation) {
    this.driveTrain = driveTrain;
    this.forwardSpeedCalculation = forwardSpeedCalculation;
    this.rotationSpeedCalculation = rotationSpeedCalculation;
    addRequirements(driveTrain);
  }

  @Override
  public void execute() {
    driveTrain.arcadeDrive(forwardSpeedCalculation.getAsDouble(),
        rotationSpeedCalculation.getAsDouble());
  }

  @Override
  public void end(final boolean interrupted) {
    driveTrain.stopDrive();
  }
}
