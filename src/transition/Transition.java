package transition;

import org.javatuples.Tuple;

import java.util.Objects;

/**
 * <h2>Transition</h2>
 * <p>
 * Transition class is used
 * to represent a transition
 * between the states of a
 * formal computational machine.
 * <p>
 * Transition takes a tuple of
 * parameters, depending of which
 * type of machine we are simulating,
 * and returns another tuple.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class Transition implements Comparable<Transition> {
  /**
   * Tuple of parameters of
   * the transition.
   */
  private Tuple currentState;

  /**
   * Tuple of the result of
   * the transition.
   */
  private Tuple nextState;

  /**
   * Constructor of the class.
   * <p>
   * It takes the tuple of the
   * parameters and the tuple of
   * the result.
   *
   * @param currentState current state of the transition.
   * @param nextState    next state of the transition.
   * @throws NullPointerException if currentState or nextState
   *                              is {@code null}.
   */
  public Transition(Tuple currentState, Tuple nextState) {
    setCurrentState(currentState);
    setNextState(nextState);
  }

  /**
   * Gets the current state of the
   * transition.
   *
   * @return current state of the transition.
   */
  public Tuple getCurrentState() {
    return currentState;
  }

  /**
   * Sets the current state of the
   * transition.
   *
   * @param currentState that we are going to set.
   * @throws NullPointerException if currentState is  {@code null}.
   */
  public void setCurrentState(Tuple currentState) {
    if (currentState == null)
      throw new NullPointerException("current state can not be null.");

    this.currentState = currentState;
  }

  /**
   * Gets the next state of the transition.
   *
   * @return next state of the transition.
   */
  public Tuple getNextState() {
    return nextState;
  }

  /**
   * Sets the next state of the
   * transition to the specified value.
   *
   * @param nextState of the transition.
   * @throws NullPointerException if nextState is  {@code null}.
   */
  public void setNextState(Tuple nextState) {
    if (nextState == null)
      throw new NullPointerException("next state can not be null.");

    this.nextState = nextState;
  }

  /**
   * Tests if two transitions
   * are equals.
   *
   * @param o object to be compared
   * @return {@code true} if transition
   * are equals.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transition that = (Transition) o;
    return Objects.equals(currentState, that.currentState) &&
      Objects.equals(nextState, that.nextState);
  }

  /**
   * Hash code of the transition.
   *
   * @return hash code of the transition.
   */
  @Override
  public int hashCode() {
    return Objects.hash(currentState, nextState);
  }

  /**
   * Method that returns the transition
   * in a string representation, like
   * this:
   * <p>
   * (i0, i1, ... , in) → (o0, 01, ... , 0m)
   *
   * @return transition in a string
   * representation.
   */
  @Override
  public String toString() {
    String transition = "(";
    int index = 0;
    for (Object elem : getCurrentState()) {
      transition += index < getCurrentState().getSize() - 1 ?
        elem.toString() + ", " :
        elem.toString();
      index += 1;
    }
    transition += ") → (";
    index = 0;
    for (Object elem : getNextState()) {
      transition += index < getNextState().getSize() - 1 ?
        elem.toString() + ", " :
        elem.toString();
      index += 1;
    }
    transition += ")";
    return transition;
  }

  @Override
  public int compareTo(Transition o) {
    int currentStateComp = getCurrentState().compareTo(o.getCurrentState());
    int nextStateComp = getNextState().compareTo(o.getNextState());

    if (currentStateComp == 0
            && nextStateComp == 0) {
      return 0;
    }
    if (currentStateComp != 0) {
      return currentStateComp;
    } else {
      return nextStateComp;
    }
  }
}
