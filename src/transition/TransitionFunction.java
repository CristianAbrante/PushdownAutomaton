package transition;

import ComputationalSet.ComputationalSet;
import org.javatuples.Tuple;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;


/**
 * <h2>Transition Function</h2>
 * <p>
 * Transition function is used
 * by certain types of computational
 * machines to determine the changes
 * of the internal state of the machine,
 * depending of the current state and
 * other external factors.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class TransitionFunction
        extends ComputationalSet<Transition> {

  /**
   * Empty constructor of the class.
   */
  public TransitionFunction() {
    super();
  }

  /**
   * Constructor of the class.
   * <p>
   * It takes only one transition.
   *
   * @param transition that the function is going
   *                   to store.
   */
  public TransitionFunction(Transition transition) {
    super(transition);
  }

  /**
   * Constructor of the class.
   * <p>
   * It takes a whole collection of
   * transitions, that the function is
   * going to store.
   *
   * @param transitions collection of transitions
   *                    to add.
   */
  public TransitionFunction(Collection<Transition> transitions) {
    super(transitions);
  }

  /**
   * Returns if the function has a
   * return value depending on the
   * specified current state.
   *
   * @param currentState
   * @return {@code true} if function has the next
   * state.
   */
  public boolean hasNextState(Tuple currentState) {
    return !getNextState(currentState).isEmpty();
  }

  /**
   * Returns a list of next states given a current
   * state.
   *
   * @param currentState of the transition.
   * @return next state of the function or {@code null}
   * if there is not next state.
   */
  public Set<Transition> getNextState(Tuple currentState) {
    if (currentState == null)
      throw new NullPointerException("current state can not be null.");

    Set<Transition> transitions = new TreeSet<>();
    for (Transition t : this) {
      if (t.getCurrentState().equals(currentState)) {
        transitions.add(t);
      }
    }
    return transitions;
  }

  /**
   * return the number of transitions
   * of the function.
   *
   * @return number of transitions.
   */
  public int numberOfTransitions() {
    return super.size();
  }

  /**
   * Return the string representation
   * of the transition function.
   *
   * @return string representation of the
   * function.
   */
  @Override
  public String toString() {
    String transitionFunction = "";
    for (Transition t: this) {
      transitionFunction += t.toString() + "\n";
    }
    return transitionFunction;
  }
}
