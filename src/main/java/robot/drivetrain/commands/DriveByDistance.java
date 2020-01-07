package robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drivetrain.DriveTrain;

import java.util.function.Supplier;

public class DriveByDistance  extends CommandBase {
    private final DriveTrain driveTrain;
    private final Supplier<Number> forwardSpeedCalculation;
    private final Supplier<Number> rotationSpeedCalculation;
    private final double distance;



    public DriveByDistance(final DriveTrain driveTrain, final Supplier<Number> forwardSpeedCalculation, final Supplier<Number> rotationSpeedCalculation, final double distance){
        this.driveTrain = driveTrain;
        this.forwardSpeedCalculation = forwardSpeedCalculation;
        this.rotationSpeedCalculation = rotationSpeedCalculation;
        this.distance = distance;
    }

    @Override
    public void initialize() {
        driveTrain.resetEncoder();
        driveTrain.setTalonPosition(distance);
    }

    @Override
    public boolean isFinished() {
        return  (Math.abs(driveTrain.getLeftDistance() - distance) < DriveTrain.TOLERANCE && Math.abs(driveTrain.getRightDistance() - distance) < DriveTrain.TOLERANCE );
    }







}
