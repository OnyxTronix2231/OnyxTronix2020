package robot.vision.limelight.exception;

/**
 * TargetNotFoundException is a RuntimeException that is thrown whenever a trial to fetch a vision target from Limelight
 * is not successful
 */
public class TargetNotFoundException extends RuntimeException {

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
