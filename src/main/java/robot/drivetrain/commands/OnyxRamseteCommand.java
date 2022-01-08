package robot.drivetrain.commands;

import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class OnyxRamseteCommand extends CommandBase {
  private final Timer timer = new Timer();
  private final Supplier<Trajectory> trajectorySupplier;
  private final Supplier<Pose2d> pose2dSupplier;
  private final BiConsumer<Double, Double> outputVoltage;
  private final RamseteController follower;
  private final DifferentialDriveKinematics kinematics;
  private Trajectory trajectory;

  public OnyxRamseteCommand(final Supplier<Trajectory> trajectorySupplier, final Supplier<Pose2d> pose2dSupplier,
                            final BiConsumer<Double, Double> outputVoltage, final RamseteController follower,
                            final DifferentialDriveKinematics kinematics, final Subsystem... requirements) {
    this.trajectorySupplier = requireNonNullParam(trajectorySupplier, "trajectory", "RamseteCommand");
    this.pose2dSupplier = requireNonNullParam(pose2dSupplier, "pose", "RamseteCommand");
    this.outputVoltage = requireNonNullParam(outputVoltage, "outputVoltage", "RamseteCommand");
    this.follower = requireNonNullParam(follower, "follower", "RamseteCommand");
    this.kinematics = requireNonNullParam(kinematics, "kinematics", "RamseteCommand");

    addRequirements(requirements);
  }

  @Override
  public void initialize() {
    trajectory = trajectorySupplier.get();
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    final double currentTime = timer.get();

    final DifferentialDriveWheelSpeeds targetWheelSpeeds = kinematics.toWheelSpeeds(
        follower.calculate(pose2dSupplier.get(), trajectory.sample(currentTime)));

    final double leftSpeedSetpoint = targetWheelSpeeds.leftMetersPerSecond;
    final double rightSpeedSetpoint = targetWheelSpeeds.rightMetersPerSecond;

    outputVoltage.accept(leftSpeedSetpoint, rightSpeedSetpoint);
  }

  @Override
  public void end(final boolean interrupted) {
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return timer.hasPeriodPassed(trajectory.getTotalTimeSeconds());
  }
}
