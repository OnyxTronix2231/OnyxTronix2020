package robot.climber.commands;

import static robot.drivetrain.DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.climber.Climber;

import java.util.function.DoubleSupplier;

public class ClimbByDistance extends CommandBase {

  private final Climber climber;
  private final DoubleSupplier distance;
  private double Target;


  public ClimbByDistance(Climber climber, DoubleSupplier distance) {
    this.climber = climber;
    this.distance = distance;
    addRequirements(climber);
  }

  @Override
  public void initialize() {
    climber.initMotionProfileSlot(DRIVE_BY_DISTANCE_SLOT);
    Target = climber.getTargetFromDistance(distance.getAsDouble());
  }

  @Override
  public void execute() {
    climber.climbByMotionMagic(Target);
  }

  @Override
  public boolean isFinished() {
    return climber.isClimberOnTarget(Target);
  }

  @Override
  public void end(final boolean interrupted) {
    climber.stopMotor();
  }
}

