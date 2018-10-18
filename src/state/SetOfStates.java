package state;

import java.util.Collection;
import java.util.TreeSet;

/**
 * <h2>SetOfStates</h2>
 *
 * This is an special set for
 * the states of a computation
 * model.
 *
 * Computational models which
 * uses states could be DFA,
 * NFA, PDA or Turing Machine.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class SetOfStates extends TreeSet<State> {

  /**
   * Constructor of the
   * class.
   *
   * It creates an empty set
   * of states.
   */
  public SetOfStates() {
    super();
  }

  /**
   * Constructor of the
   * class.
   *
   * It creates the set of
   * states from the given
   * collection.
   *
   * @param states of the set.
   * @throws NullPointerException
   *         if identifier is {@code null}.
   *
   */
  public SetOfStates(Collection<State> states) {
    super(states);
  }

  /**
   * Constructor of the
   * class.
   *
   * It creates the set from
   * the given state.
   *
   * @param state which we want to create
   *              the set from.
   */
  public SetOfStates(State state) {
    super();
    add(state);
  }
}
