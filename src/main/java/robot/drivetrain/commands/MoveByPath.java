//package robot.drivetrain.commands;
//
//import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.RAMSETE_B;
//import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.RAMSETE_ZETA;
//
//import edu.wpi.first.wpilibj.controller.RamseteController;
//import robot.drivetrain.DriveTrain;
//import robot.drivetrain.OnyxTrajectoryGenerator;
//import robot.drivetrain.Path;

//public class MoveByPath extends OnyxRamseteCommand {
//  public MoveByPath(final DriveTrain driveTrain,
//                    final Path path) {
//    super(() -> driveTrain.getTrajectoryGenerator().getTrajectoryFromPoseList(path,
//        driveTrain.getComponents(), path.getPoseAt()),
//        driveTrain::getPose,
//        new RamseteController(RAMSETE_B, RAMSETE_ZETA),
//        driveTrain.getKinematics(),
//        driveTrain::getWheelSpeeds,
//        driveTrain::tankDriveVolts,
//        driveTrain.getFeedForward(),
//        driveTrain);
//  }
//}
