package robot.drivetrain.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.DriveTrainConstants;

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
        driveTrain.resetEncoder();
        driveTrain.initializeMotionMagic(distance.getAsDouble());
        isFinished = false;
        successfulCheckCounter = 0;
    }

    @Override
    public void execute() {
        if (Math.abs(driveTrain.getLeftDistance() - distance.getAsDouble()) < DriveTrainConstants.TOLERANCE &&
                Math.abs(driveTrain.getRightDistance() - distance.getAsDouble()) < DriveTrainConstants.TOLERANCE) {
            if (successfulCheckCounter < DriveTrainConstants.MIN_CHECKS) {
                successfulCheckCounter++;
            } else {
                isFinished = true;
            }
        } else {
            successfulCheckCounter = 0;
        }
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stopDrive();
    }

    @Override
    public boolean isFinished() {
        return (Math.abs(driveTrain.getLeftDistance() - distance.getAsDouble()) < DriveTrainConstants.TOLERANCE &&
                Math.abs(driveTrain.getRightDistance() - distance.getAsDouble()) < DriveTrainConstants.TOLERANCE) &&
                isFinished;
    }
}
