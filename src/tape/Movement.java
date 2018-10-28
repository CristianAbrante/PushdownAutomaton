package tape;

/**
 * Enum representing
 * the possible movements
 * of a tape.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public enum Movement {
  LEFT(-1),
  RIGHT(1),
  STOP(0);

  /**
   * Value of the movement.
   */
  public int value;

  /**
   * Private constructor
   * of the elements.
   *
   * @param value that the element
   *              is going to have.
   */
  private Movement(int value) {
    this.value = value;
  }
}