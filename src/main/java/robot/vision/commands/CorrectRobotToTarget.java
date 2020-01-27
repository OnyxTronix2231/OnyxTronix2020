package robot.vision.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;
import robot.vision.VisionConstants;
import robot.vision.VisionTarget;
import java.util.function.Supplier;

public class CorrectRobotToTarget extends CommandBase {

  private final DriveTrain driveTrain;
  private final Supplier<VisionTarget> visionTargetSupplier;

  public CorrectRobotToTarget(final DriveTrain driveTrain, final Supplier<VisionTarget> visionTargetSupplier) {
    this.driveTrain = driveTrain;
    this.visionTargetSupplier = visionTargetSupplier;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
    driveTrain.resetGyroPID();
    driveTrain.setGyroPIDSetPoint(driveTrain.getNavXYaw() + 90);
  }

  @Override
  public void execute() {
    driveTrain.arcadeDrive(0, -driveTrain.calculateGyroPIDProduct());
  }

  @Override
  public boolean isFinished() {
    return driveTrain.isGyroPIDatSetPoint();
  }
}
