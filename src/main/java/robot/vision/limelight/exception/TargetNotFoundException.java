package robot.vision.limelight.exception;

/**
 * TargetNotFoundException is ab Exception that is thrown whenever a trial to fetch a vision target from Limelight
 * is not successful
 *
 * @since 2020-01-10
 */
public class TargetNotFoundException extends Exception {

  /**
   * Default Constructor
   * <p>The exception is made with the default message "Limelight target was not found."</p>
   */
  public TargetNotFoundException() {
    super("Limelight target was not found.");
  }

  /**
   * Default Constructor With custom message to be displayed when thrown.
   *
   * @param message Message to be displayed when the exception is thrown.
   */
  public TargetNotFoundException(String message) {
    super(message);
  }
}
