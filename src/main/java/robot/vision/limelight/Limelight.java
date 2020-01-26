package robot.vision.limelight;

import static robot.vision.limelight.LimelightConstants.DEFAULT_VALUE;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import robot.vision.limelight.enums.LimelightLedMode;
import robot.vision.limelight.enums.LimelightOperationMode;
import robot.vision.limelight.enums.LimelightStreamMode;
import robot.vision.limelight.target.Corner;
import robot.vision.limelight.target.LimelightTarget;
import robot.vision.limelight.target.LimelightTargetWithRawCorners;

/**
 * Limelight is a wrapper singleton for interacting with the Limelight camera.
 *
 * @since 2020-01-10
 */
public class Limelight {

  private static Limelight instance = null;
  private final NetworkTable networkTable;

  private Limelight() {
    networkTable = NetworkTableInstance.getDefault().getTable("limelight");
  }

  /**
   * Returns the instance of The singleton
   *
   * @return Limelight default instance
   */
  public static Limelight getInstance() {
    if (instance == null) {
      instance = new Limelight();
    }
    return instance;
  }

  /**
   * Sets the pipeline limelight would use to process the target
   *
   * @exception IndexOutOfBoundsException
   * @param pipelineIndex index of pipeline. Must be between 0 - 9
   */
  public void setPipeline(final int pipelineIndex) {
    if(pipelineIndex >= 0 || pipelineIndex <=9) {
      networkTable.getEntry("pipeline").setNumber(pipelineIndex);
    } else {
      throw new IndexOutOfBoundsException(String.format("Index %d is invalid. Pipeline indices must be between 0 - 9",
        pipelineIndex));
    }
  }

  /**
   * Sets The Operation Mode
   *
   * <p>Limelight can be used either as a vision processor or a driver camera</p>
   *
   * @param operationMode Desired Operation Mode
   */
  public void setOperationMode(final LimelightOperationMode operationMode) {
    networkTable.getEntry("camMode").setNumber(operationMode.value);
  }

  /**
   * Sets The Led Mode
   * @param ledMode Desired Led Mode
   */
  public void setLedMode(final LimelightLedMode ledMode) {
    networkTable.getEntry("ledMode").setNumber(ledMode.value);
  }

  /**
   * Sets The Stream Mode if a USB webcam is attached to Limelight
   *
   * <p>Limelight mode can be set to one of the following Modes:
   * <ul>
   *   <li>Standard - The two cameras are streamed side-by-side.</li>
   *   <li>PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream</li>
   *   <li>PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream</li>
   * </ul></p>
   * @param streamMode Desired Stream Mode
   */
  public void setStreamMode(final LimelightStreamMode streamMode) {
    networkTable.getEntry("stream").setNumber(streamMode.value);
  }

  /**
   * Set whether To Take Snapshots or not.
   * <p>Limelight can take snapshots during the match.
   * If parameter is set to True, then Limelight would take two snapshots per second</p>
   * @param takeSnapshots
   */
  public void setTakeSnapshots(final boolean takeSnapshots) {
    networkTable.getEntry("snapshot").setNumber(takeSnapshots ? 1 : 0);
  }

  /**
   * Check if target exists
   *
   * @return if target was found return true
   */
  public boolean targetFound() {
    if(networkTable.getEntry("tv").getDouble(DEFAULT_VALUE) < 1) {
      return false;
    }
    return true;
  }

  /**
   * Retrieves a Target From Limelight
   *
   * @return Vision Target retrieved from Limelight
   */
  public LimelightTarget getTarget() {
    if(targetFound() == false) {
      return null;
    }

    final double horizontalOffsetToCrosshair = networkTable.getEntry("tx").getDouble(DEFAULT_VALUE);
    final double verticalOffsetToCrosshair = networkTable.getEntry("ty").getDouble(DEFAULT_VALUE);
    final double targetArea = networkTable.getEntry("ta").getDouble(DEFAULT_VALUE);
    final double skew = networkTable.getEntry("ts").getDouble(DEFAULT_VALUE);
    final double shortSideOfFittedBoundingBox = networkTable.getEntry("tshort").getDouble(DEFAULT_VALUE);
    final double longSideOfFittedBoundingBox = networkTable.getEntry("tlong").getDouble(DEFAULT_VALUE);
    final double horizontalSideOfRoughBoundingBox = networkTable.getEntry("thor").getDouble(DEFAULT_VALUE);
    final double verticalSideOfRoughBoundingBox = networkTable.getEntry("tvert").getDouble(DEFAULT_VALUE);

    return new LimelightTarget(horizontalOffsetToCrosshair, verticalOffsetToCrosshair,
      targetArea,skew,
      shortSideOfFittedBoundingBox, longSideOfFittedBoundingBox,
      horizontalSideOfRoughBoundingBox, verticalSideOfRoughBoundingBox);
  }

  /**
   * Retrieves a Target With Raw Corners From Limelight
   *
   * @return Vision Target With Raw Corners Retrieved from Limelight
   */
  public LimelightTargetWithRawCorners getTargetWithRawCorners() {
    final LimelightTarget basicTarget = getTarget();
    final Corner[] corners;
    final double[] xcorners;
    final double[] ycorners;
    if((xcorners = networkTable.getEntry("tcornx").getDoubleArray(new double[] {-999}))[0] == -999) {
      return null;
    }
    ycorners = networkTable.getEntry("tcorny").getDoubleArray(new double[] {-999});
    corners = new Corner[xcorners.length];
    for (int i = 0; i < xcorners.length; i++) {
      corners[i] = new Corner(xcorners[i], ycorners[i]);
    }

    return new LimelightTargetWithRawCorners(basicTarget, corners);
  }
}