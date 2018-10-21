package transition;

import org.javatuples.Tuple;

import java.util.*;

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
public class TransitionFunction {
  /**
   * Map is used to store the transitions
   * in a efficient way.
   */
  private HashMap<Tuple, TreeSet<Tuple>> functionMap;

  /**
   * Empty constructor of the class.
   */
  public TransitionFunction() {
    functionMap = new HashMap<Tuple, TreeSet<Tuple>>();
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
    this();
    addTransition(transition);
  }

  /**
   * Constructor of the class.
   * <p>
   * It takes a whole collection of
   * transitions, that the function is
   * going to store.
   *
   * @param transitions
   */
  public TransitionFunction(Collection<Transition> transitions) {
    this();
    addTransitions(transitions);
  }

  /**
   * Adds the specified transition
   * to the transition function.
   *
   * @param transition that we want to add.
   * @throws NullPointerException if transition is {@code null}
   */
  public void addTransition(Transition transition) {
    if (transition == null)
      throw new NullPointerException("transition can be null.");

    Tuple currentState = transition.getCurrentState();
    TreeSet<Tuple> nextState = hasNextState(currentState) ?
      getNextState(currentState) :
      new TreeSet<Tuple>();
    nextState.add(transition.getNextState());
    getFunctionMap().put(currentState, nextState);
  }

  /**
   * Add a collection of transitions to
   * the transition function.
   *
   * @param transitions that we want to add.
   */
  public void addTransitions(Collection<Transition> transitions) {
    if (transitions == null)
      throw new NullPointerException("transitions can not be null.");

    for (Transition transition : transitions)
      addTransition(transition);
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
    return getNextState(currentState) != null;
  }

  /**
   * Returns a list of next states given a current
   * state.
   *
   * @param currentState of the transition.
   * @return next state of the function or {@code null}
   * if there is not next state.
   */
  public TreeSet<Tuple> getNextState(Tuple currentState) {
    if (currentState == null)
      throw new NullPointerException("current state can not be null.");

    return getFunctionMap().get(currentState);
  }

  /**
   * Returns the list of transitions
   * of the functions.
   *
   * @return list of transitions of the
   * function.
   */
  public List<Transition> getTransitions() {
    List<Transition> transitions = new ArrayList<Transition>();
    for (Map.Entry<Tuple, TreeSet<Tuple>> transition :
      getFunctionMap().entrySet()) {
      for (Tuple nextState : transition.getValue()) {
        transitions.add(new Transition(transition.getKey(), nextState));
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
  public int getNumberOfTransitions() {
    return getTransitions().size();
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
    for (Transition transition : getTransitions()) {
      transitionFunction += transition.toString() + "\n";
    }
    return transitionFunction;
  }

  /**
   * Private getter of the function map
   *
   * @return teh function map.
   */
  private HashMap<Tuple, TreeSet<Tuple>> getFunctionMap() {
    return functionMap;
  }
}
