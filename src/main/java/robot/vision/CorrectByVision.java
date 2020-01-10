package robot.vision;

import static robot.vision.VisionConstants.KD;
import static robot.vision.VisionConstants.KI;
import static robot.vision.VisionConstants.KP;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;
import robot.vision.limelight.Limelight;
import robot.vision.limelight.enums.LimelightLedMode;
import robot.vision.limelight.target.LimelightTarget;

public class CorrectByVision extends CommandBase {

  private final DriveTrain driveTrain;
  private final XboxController driveJoystick;
  private final PIDController pidController;

  public CorrectByVision(final DriveTrain driveTrain, final XboxController driveJoystick) {
    this.driveTrain = driveTrain;
    this.driveJoystick = driveJoystick;
    pidController = new PIDController(KP, KI, KD);
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
    Limelight.getInstance().setLedMode(LimelightLedMode.useCurrentPipelineMode);
    pidController.setSetpoint(0);
  }

  @Override
  public void execute() {
    final LimelightTarget target = Limelight.getInstance().getTarget();
    driveTrain.arcadeDrive(driveJoystick.getY(GenericHID.Hand.kLeft)
      ,-pidController.calculate(target.getHorizontalOffsetToCrosshair()));
  }

  @Override
  public void end(boolean interrupted) {
    Limelight.getInstance().setLedMode(LimelightLedMode.forceOff);
  }

  @Override
  public boolean isFinished() {
    return driveJoystick.getX(GenericHID.Hand.kRight) != 0;
  }
}
