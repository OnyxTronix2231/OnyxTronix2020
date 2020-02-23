package robot.climber.commands;

import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.climber.Climber;

import java.util.function.DoubleSupplier;

public class ClimbByDistance extends CommandBase {

  private final Climber climber;
  private final DoubleSupplier distance;
  private double target;

  public ClimbByDistance(final Climber climber,final DoubleSupplier distance) {
    this.climber = climber;
    this.distance = distance;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    climber.initMotionProfileSlot(DRIVE_BY_DISTANCE_SLOT);
    target = climber.getTargetFromDistance(distance.getAsDouble());
  }

  @Override
  public void execute() {
    climber.climbByMotionMagic(target);
  }

  @Override
  public boolean isFinished() {
    return climber.isClimberOnTarget(target);
  }

  @Override
  public void end(final boolean interrupted) {
    climber.stopMotor();
  }
}

