package robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.DriveTrainConstants;

import java.util.function.Supplier;

public class DriveByDistance extends CommandBase {

    private final DriveTrain driveTrain;
    private final Supplier<Number> forwardSpeedCalculation;
    private final Supplier<Number> rotationSpeedCalculation;
    private final double distance;
    private boolean isFinished;
    private int successfulCheckCounter;

    public DriveByDistance(final DriveTrain driveTrain, final Supplier<Number> forwardSpeedCalculation, final Supplier<Number> rotationSpeedCalculation, final double distance) {
        this.driveTrain = driveTrain;
        this.forwardSpeedCalculation = forwardSpeedCalculation;
        this.rotationSpeedCalculation = rotationSpeedCalculation;
        this.distance = distance;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.resetEncoder();
        driveTrain.initializeMotionMagic(distance);
        isFinished = false;
        successfulCheckCounter = 0;
    }

    @Override
    public void execute() {
        if (Math.abs(driveTrain.getLeftDistance() - distance) < DriveTrainConstants.TOLERANCE && Math.abs(driveTrain.getRightDistance() - distance) < DriveTrainConstants.TOLERANCE) {
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
        driveTrain.stop();
    }

    @Override
    public boolean isFinished() {
        return (Math.abs(driveTrain.getLeftDistance() - distance) < DriveTrainConstants.TOLERANCE && Math.abs(driveTrain.getRightDistance() - distance) < DriveTrainConstants.TOLERANCE);
    }
}
