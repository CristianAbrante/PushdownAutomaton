package state;

import java.util.Objects;

/**
 * <h2>State</h2>
 *
 * An state is a certain
 * information about the
 * point where the computational
 * model is processing.
 *
 * Computational models which
 * uses states could be DFA,
 * NFA, PDA or Turing Machine.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class State implements Comparable<State> {
  /**
   * String that identifies the state.
   */
  private String identifier;

  /**
   * Constructor of state.
   *
   * It takes the identifier of
   * the state as parameter.
   *
   * @param identifier of the state.
   * @throws NullPointerException
   *         if identifier is {@code null}.
   */
  public State(String identifier) {
    setIdentifier(identifier);
  }

  /**
   * Setter for the identifier of
   * the state.
   *
   * @param identifier of the state.
   * @throws NullPointerException
   *         if identifier is {@code null}.
   */
  public void setIdentifier(String identifier) {
    checkIfNull(identifier);
    this.identifier = identifier;
  }

  /**
   * Returns the representation
   * of the state as a string.
   *
   * @return the state as a string.
   */
  @Override
  public String toString() {
    return identifier;
  }

  /**
   * Compare two states.
   *
   * @param o other state we want
   *          to compare.
   * @return {@see String.compareTo()}
   */
  @Override
  public int compareTo(State o) {
    return toString().compareTo(o.toString());
  }

  /**
   * Checks if two states are equal.
   *
   * @param o object we want to compare.
   * @return {@code true} if other is a
   *         state and is equal.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof State)) return false;
    State state = (State) o;
    return Objects.equals(toString(), state.toString());
  }

  /**
   * Create a hash identifier
   * for the state.
   *
   * @return the hash identifier.
   */
  @Override
  public int hashCode() {
    return Objects.hash(identifier);
  }

  /**
   * Supply method used to compare if
   * identifier is null.
   *
   * @param identifier we want to check.
   * @throws NullPointerException
   *         if identifier is {@code null}.
   */
  private void checkIfNull(String identifier) {
    if (identifier == null) {
      throw new NullPointerException("identifier can not be null");
    }
  }
}
