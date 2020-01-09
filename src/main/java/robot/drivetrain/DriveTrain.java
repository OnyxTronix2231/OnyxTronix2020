package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

    private final BasicDriveTrainComponents components;

    public DriveTrain(final BasicDriveTrainComponents components) {
        this.components = components;
        CommandScheduler.getInstance().registerSubsystem(this);
    }

    public void stopDrive() {
        components.getDifferentialDrive().stopMotor();
    }

    public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
        components.getDifferentialDrive().arcadeDrive(forwardSpeed, rotationSpeed);
    }

    public void initializeDriveByDistance(final double distance) {
        components.getLeftMasterMotor().selectProfileSlot(DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT,
                DriveTrainConstants.PRIMARY_PID);
        components.getLeftMasterMotor().set(ControlMode.MotionMagic, cmToEncoderUnits(distance)
                        + components.getLeftMasterMotor().getSelectedSensorPosition(),
                DemandType.ArbitraryFeedForward, DriveTrainConstants.ARB_FEED_FORWARD);

        components.getRightMasterMotor().selectProfileSlot(DriveTrainConstants.DRIVE_BY_DISTANCE_SLOT,
                DriveTrainConstants.PRIMARY_PID);
        components.getRightMasterMotor().set(ControlMode.MotionMagic, cmToEncoderUnits(distance)
                        + components.getRightMasterMotor().getSelectedSensorPosition(),
                DemandType.ArbitraryFeedForward, DriveTrainConstants.ARB_FEED_FORWARD);
    }

    public double cmToEncoderUnits(double cm) {
        return DriveTrainConstants.ENCODER_UNITS / (cm / ( DriveTrainConstants.PERIMETER));
    }

    public double getLeftDistance() {
        return components.getLeftMasterMotor().getSelectedSensorPosition() / DriveTrainConstants.ENCODER_UNITS *
                DriveTrainConstants.PERIMETER;
    }

    public double getRightDistance() {
        return components.getRightMasterMotor().getSelectedSensorPosition() / DriveTrainConstants.ENCODER_UNITS *
                DriveTrainConstants.PERIMETER;
    }

    public boolean isDriveOnTarget(){
        return Math.abs(components.getLeftMasterMotor().getClosedLoopError()) < DriveTrainConstants.TOLERANCE &&
                Math.abs(components.getRightMasterMotor().getClosedLoopError()) < DriveTrainConstants.TOLERANCE;
    }

}