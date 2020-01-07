package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private final BasicDriveTrainComponents components ;
    public static final double ENCODER_UNITES =  1023.0 ;
    public static final double RADIUS = 7.5; // tuning is required
    public static final double TOLERANCE = 3;
    private static final int PRIMARY_PID = 0;
    private static final int MOTION_MAGIC_PID_SLOT = 0;
    private static final double ARB_FEED_FORWARD = 0.04; // tuning is required

    public DriveTrain(final BasicDriveTrainComponents components) {
        this.components = components;
    }

    public void stop() {
        components.getDifferentialDrive().stopMotor();
    }

    public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
        components.getDifferentialDrive().arcadeDrive(forwardSpeed, rotationSpeed);
    }

    public void resetEncoder(){
        components.getLeftMaster().setSelectedSensorPosition(0);
        components.getRightMaster().setSelectedSensorPosition(0);
    }

    public void initializeMotionMagic(final double distance) {
        components.getLeftMaster().selectProfileSlot(MOTION_MAGIC_PID_SLOT, PRIMARY_PID);
        components.getRightMaster().selectProfileSlot(MOTION_MAGIC_PID_SLOT, PRIMARY_PID);
        components.getLeftMaster().set(ControlMode.MotionMagic, cmToEncoderUnits(distance),
                DemandType.ArbitraryFeedForward, ARB_FEED_FORWARD);
        components.getRightMaster().set(ControlMode.MotionMagic, cmToEncoderUnits(distance),
                DemandType.ArbitraryFeedForward, ARB_FEED_FORWARD);
    }


    public double cmToEncoderUnits(double cm){
        return ENCODER_UNITES / (cm / (2 * RADIUS * Math.PI));
    }

    public double  getLeftDistance(){
        return components.getLeftMaster().getSelectedSensorPosition() /ENCODER_UNITES * RADIUS *2 * Math.PI ;
    }

    public double  getRightDistance(){
        return components.getRightMaster().getSelectedSensorPosition() /ENCODER_UNITES * RADIUS *2 * Math.PI ;
    }


}







