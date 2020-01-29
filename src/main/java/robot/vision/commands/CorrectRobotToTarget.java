package robot.vision.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.RotateToNavXOffset;
import robot.vision.VisionConstants;
import robot.vision.VisionTarget;
import java.util.function.Supplier;

public class CorrectRobotToTarget extends RotateToNavXOffset {

  public CorrectRobotToTarget(final DriveTrain driveTrain, final Supplier<VisionTarget> visionTargetSupplier) {
    super(driveTrain, () -> visionTargetSupplier.get().getHorizontalOffset());
  }
}
