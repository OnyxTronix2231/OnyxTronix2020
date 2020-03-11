package robot.drivetrain.commands;

import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.TRAJECTORY_P;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class OnyxRamseteCommand extends CommandBase {
  public final Timer timer = new Timer();
  private final Supplier<Trajectory> trajectorySupplier;
  private final Supplier<Pose2d> pose2dSupplier;
  private final BiConsumer<Double, Double> outputVoltage;
  private final RamseteController controller;
  private final DifferentialDriveKinematics kinematics;
  private final Supplier<DifferentialDriveWheelSpeeds> wheelSpeeds;
  private final PIDController leftController;
  private final PIDController rightController;
  private final SimpleMotorFeedforward feedforward;
  private double prevTime;
  private Trajectory trajectory;
  private DifferentialDriveWheelSpeeds prevSpeeds;
  private static NetworkTableEntry networkTableEntry;

  public OnyxRamseteCommand(final Supplier<Trajectory> trajectorySupplier,
                            final Supplier<Pose2d> pose2dSupplier,
                            final RamseteController controller,
                            final DifferentialDriveKinematics kinematics,
                            final Supplier<DifferentialDriveWheelSpeeds> wheelSpeeds,
                            final BiConsumer<Double, Double> outputVoltage,
                            final SimpleMotorFeedforward feedforward,
                            final Subsystem... requirements) {
    this.trajectorySupplier = requireNonNullParam(trajectorySupplier, "trajectory", "RamseteCommand");
    this.pose2dSupplier = requireNonNullParam(pose2dSupplier, "pose", "RamseteCommand");
    this.controller = requireNonNullParam(controller, "controller", "RamseteCommand");
    this.kinematics = requireNonNullParam(kinematics, "kinematics", "RamseteCommand");
    this.wheelSpeeds = requireNonNullParam(wheelSpeeds, "wheelSpeeds", "RamseteCommand");
    this.outputVoltage = requireNonNullParam(outputVoltage, "outputVolts", "RamseteCommand");
    this.feedforward = feedforward;
    leftController = new PIDController(TRAJECTORY_P, 0, 0);
    rightController = new PIDController(TRAJECTORY_P, 0, 0);

    try {
      networkTableEntry =
          Shuffleboard.getTab("Odometry").add("9", leftController.getPositionError()).getEntry();
    } catch (Exception e) {

    }
    addRequirements(requirements);
  }

  @Override
  public void initialize() {
    try {
      trajectory = trajectorySupplier.get();
    } catch (Exception e) {
      System.out.println(e);
      this.cancel();
    }
    prevTime = 0;
    var initialState = trajectory.sample(0);
    prevSpeeds = kinematics.toWheelSpeeds(
        new ChassisSpeeds(initialState.velocityMetersPerSecond, 0,
            initialState.curvatureRadPerMeter * initialState.velocityMetersPerSecond));
    timer.reset();
    timer.start();
    leftController.reset();
    rightController.reset();
  }

  @Override
  public void execute() {
    final double currentTime = timer.get();
    final double deltaTime = currentTime - prevTime;

    final DifferentialDriveWheelSpeeds targetWheelSpeeds = kinematics.toWheelSpeeds(
        controller.calculate(pose2dSupplier.get(), trajectory.sample(currentTime)));

    final double leftSpeedSetpoint = targetWheelSpeeds.leftMetersPerSecond;
    final double rightSpeedSetpoint = targetWheelSpeeds.rightMetersPerSecond;

    final double leftFeedforward =
        feedforward.calculate(leftSpeedSetpoint,
            (leftSpeedSetpoint - prevSpeeds.leftMetersPerSecond) / deltaTime);

    final double rightFeedforward =
        feedforward.calculate(rightSpeedSetpoint,
            (rightSpeedSetpoint - prevSpeeds.rightMetersPerSecond) / deltaTime);

    final double leftOutput = leftFeedforward
        + leftController.calculate(wheelSpeeds.get().leftMetersPerSecond,
        leftSpeedSetpoint);

    final double rightOutput = rightFeedforward
        + rightController.calculate(wheelSpeeds.get().rightMetersPerSecond,
        rightSpeedSetpoint);

    outputVoltage.accept(leftOutput, rightOutput);

    networkTableEntry.setValue(leftController.getPositionError());
    prevTime = currentTime;
    prevSpeeds = targetWheelSpeeds;

  }

  @Override
  public void end(final boolean interrupted) {
    timer.stop();
    outputVoltage.accept(0.0, 0.0);
  }

  @Override
  public boolean isFinished() {
    return timer.hasPeriodPassed(trajectory.getTotalTimeSeconds());
  }
}
