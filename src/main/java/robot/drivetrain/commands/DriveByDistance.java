package robot.drivetrain.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveByDistance extends CommandBase {

    private final DriveTrain driveTrain;
    private final DoubleSupplier distance;
    private boolean isFinished;
    private int successfulCheckCounter;

    public DriveByDistance(final DriveTrain driveTrain, final DoubleSupplier distance) {
        this.driveTrain = driveTrain;
        this.distance = distance;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.initializeDriveByDistance(distance.getAsDouble());
        isFinished = false;
        successfulCheckCounter = 0;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stopDrive();
    }

    @Override
    public boolean isFinished() {
        return driveTrain.isDriveOnTarget();

    }
}
