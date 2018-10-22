package state;

import java.util.*;

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
   * It creates the set from
   * the given state.
   *
   * @param state which we want to create
   *              the set from.
   */
  public SetOfStates(State state) {
    this();
    add(state);
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
    this();
    addAll(states);
  }

  /**
   * Returns the given state represented
   * by the identifier.
   *
   * @param identifier
   * @return
   */
  public State getStateById(String identifier) {
    for (State state : this) {
      if (state.toString().equals(identifier)) {
        return state;
      }
    }
    return null;
  }

  /**
   * Tests if the set contains the
   * state by identifier of the state.
   *
   * @param identifier of the state.
   * @return {@code true} if the set contains
   *         the state.
   */
  public boolean containsById(String identifier) {
    return super.contains(new State(identifier));
  }

  /**
   * Test if the set contains all the
   * states of the collection by their
   * idl.
   *
   * @param identifiers of the states.
   * @return {@code true} if the set contains
   *         all the states.
   */
  public boolean containsAllById(Collection<String> identifiers) {
    if (identifiers == null)
      throw new NullPointerException("states can not be null.");

    for (String state : identifiers) {
      if (!super.contains(new State(state))) {
        return false;
      }
    }
    return true;
  }
}
