package robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private final BasicDriveTrainComponents components ;


    public DriveTrain(final BasicDriveTrainComponents components) {
        this.components = components;

    }

    public void arcadeDrive(final double forwardSpeed, final double rotationSpeed) {
        components.getDifferentialDrive().arcadeDrive(forwardSpeed, rotationSpeed);
    }




}
