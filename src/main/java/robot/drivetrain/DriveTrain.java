package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private final BasicDriveTrainComponents components;


    public DriveTrain(final BasicDriveTrainComponents components) {
        this.components = components;
    }

    public void stop() {
        components.getDifferentialDrive().stopMotor();
    }

    public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
        components.getDifferentialDrive().arcadeDrive(forwardSpeed, rotationSpeed);
    }

    public void resetEncoder() {
        components.getLeftMaster().setSelectedSensorPosition(0);
        components.getRightMaster().setSelectedSensorPosition(0);
    }

    public void initializeMotionMagic(final double distance) {
        components.getLeftMaster().selectProfileSlot(DriveTrainConstants.MOTION_MAGIC_PID_SLOT, DriveTrainConstants.PRIMARY_PID);
        components.getRightMaster().selectProfileSlot(DriveTrainConstants.MOTION_MAGIC_PID_SLOT, DriveTrainConstants.PRIMARY_PID);
        components.getLeftMaster().set(ControlMode.MotionMagic, cmToEncoderUnits(distance),
                DemandType.ArbitraryFeedForward, DriveTrainConstants.ARB_FEED_FORWARD);
        components.getRightMaster().set(ControlMode.MotionMagic, cmToEncoderUnits(distance),
                DemandType.ArbitraryFeedForward, DriveTrainConstants.ARB_FEED_FORWARD);
    }


    public double cmToEncoderUnits(double cm) {
        return DriveTrainConstants.ENCODER_UNITES / (cm / (2 * DriveTrainConstants.RADIUS * Math.PI));
    }

    public double getLeftDistance() {
        return components.getLeftMaster().getSelectedSensorPosition() / DriveTrainConstants.ENCODER_UNITES * DriveTrainConstants.RADIUS * 2 * Math.PI;
    }

    public double getRightDistance() {
        return components.getRightMaster().getSelectedSensorPosition() / DriveTrainConstants.ENCODER_UNITES * DriveTrainConstants.RADIUS * 2 * Math.PI;
    }


}







