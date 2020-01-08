package robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;

import java.util.function.Supplier;

public class DriveBySpeed extends CommandBase {
    private final DriveTrain driveTrain;
    private final Supplier<Number> forwardSpeedCalculation;
    private final Supplier<Number> rotationSpeedCalculation;


    public DriveBySpeed(final DriveTrain driveTrain, final Supplier<Number> forwardSpeedCalculation,
                        final Supplier<Number> rotationSpeedCalculation) {
        this.driveTrain = driveTrain;
        this.forwardSpeedCalculation = forwardSpeedCalculation;
        this.rotationSpeedCalculation = rotationSpeedCalculation;
        addRequirements(driveTrain);
    }


    @Override
    public void execute() {
        driveTrain.arcadeDrive(forwardSpeedCalculation.get().doubleValue(), rotationSpeedCalculation.get().doubleValue());
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
}
