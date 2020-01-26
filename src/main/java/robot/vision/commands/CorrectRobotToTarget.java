package robot.vision.commands;

import static robot.vision.VisionConstants.KI;
import static robot.vision.VisionConstants.KP;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;
import robot.vision.VisionConstants;
import robot.vision.VisionTarget;

public class CorrectRobotToTarget extends CommandBase {

  private final DriveTrain driveTrain;
  private final VisionTarget visionTarget;
  private final PIDController pidController;


  public CorrectRobotToTarget(final DriveTrain driveTrain, final VisionTarget visionTarget) {
    this.driveTrain = driveTrain;
    this.visionTarget = visionTarget;
    this.pidController = new PIDController(KP, KI, KP);
  }

  @Override
  public void initialize() {
    pidController.setSetpoint(driveTrain.getNavXYaw() - visionTarget.getHorizontalOffset());
  }

  @Override
  public void execute() {
    driveTrain.arcadeDrive(0, -pidController.calculate(driveTrain.getNavXYaw()));
  }

  @Override
  public boolean isFinished() {
    return pidController.atSetpoint();
  }
}
