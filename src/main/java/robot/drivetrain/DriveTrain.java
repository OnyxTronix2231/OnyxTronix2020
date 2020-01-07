package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private final BasicDriveTrainComponents components ;
    private double distanse;
    public static  final  double radios = 7.5;
    public static final double ENCODER_UNITES =  1023.0 ;
    public static final  double RADIOS = 4;
    public static final double TOLERANCE = 3;

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

    public void setTalonPosition(double cm){
        distanse = getEncoder(cm);
        components.getLeftMaster().set(ControlMode.Position,distanse);
        components.getRightMaster().set(ControlMode.Position,distanse);
    }

    public double getEncoder(double cm){
        return ENCODER_UNITES / (cm / (2 * radios * Math.PI));
    }

    public double  getLeftDistance(){
        return components.getLeftMaster().getSelectedSensorPosition() /ENCODER_UNITES *RADIOS *2 * Math.PI ;
    }

    public double  getRightDistance(){
        return components.getRightMaster().getSelectedSensorPosition() /ENCODER_UNITES *RADIOS *2 * Math.PI ;
    }




}







