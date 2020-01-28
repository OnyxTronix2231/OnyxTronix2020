package robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.navxloop.NavXPIDLoop;

import java.util.function.DoubleSupplier;

public class RotateToAngleNavX extends CommandBase {

  private final NavXPIDLoop navXPIDLoop;
  private final DriveTrain driveTrain;
  private final DoubleSupplier angleSupplier;

  public RotateToAngleNavX(NavXPIDLoop navXPIDLoop, DriveTrain driveTrain, DoubleSupplier angleSupplier) {
    this.navXPIDLoop = navXPIDLoop;
    this.driveTrain = driveTrain;
    this.angleSupplier = angleSupplier;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
    driveTrain.setGyroPIDSetPoint(angleSupplier.getAsDouble());
    navXPIDLoop.start();
  }

  @Override
  public void end(boolean interrupted) {
    if(interrupted) {
      navXPIDLoop.stop();
    }
    driveTrain.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return navXPIDLoop.isFinished();
  }
}
