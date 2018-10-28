package pushdown.transition;

import org.javatuples.Triplet;
import org.javatuples.Tuple;
import state.State;
import symbol.Symbol;
import transition.Transition;
import transition.TransitionFunction;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * <h2>PDATransitionFunction</h2>
 *
 * Transition function used for
 * pushdown automaton.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class PDATransitionFunction extends TransitionFunction {

  /**
   * Empty constructor of the class.
   */
  public PDATransitionFunction() {
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
  public PDATransitionFunction(Transition transition) {
    super(transition);
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
  public PDATransitionFunction(Collection<Transition> transitions) {
    super(transitions);
  }

  /**
   * has next state wrapper
   * for pushdown automaton.
   *
   * @param currentState
   * @param tapeSymbol
   * @param stackSymbol
   * @return
   */
  public boolean hasNextState(State currentState,
                              Symbol tapeSymbol,
                              Symbol stackSymbol) {
    return super.hasNextState(new Triplet<>(currentState, tapeSymbol, stackSymbol));
  }

  /**
   * Get next state wrapper
   * for pushdown automaton.
   *
   * @param currentState
   * @param tapeSymbol
   * @param stackSymbol
   * @return
   */
  public TreeSet<PDATransition> getNextState(State currentState,
                                          Symbol tapeSymbol,
                                          Symbol stackSymbol) {
    Triplet<State, Symbol, Symbol> currentStateTriplet =
            new Triplet<>(currentState, tapeSymbol, stackSymbol);
    Set<Transition> symbolTransitions =
            super.getNextState(currentStateTriplet);

    // sets the tuple to collect empty symbol values.
    currentStateTriplet = currentStateTriplet.setAt1(Symbol.EMPTY_SYMBOL);
    Set<Transition> emptySymbolTransitions =
            super.getNextState(currentStateTriplet);

    symbolTransitions.addAll(emptySymbolTransitions);
    TreeSet<PDATransition> pdaTransitions = new TreeSet<>();
    for (Transition t : symbolTransitions) {
      pdaTransitions.add(new PDATransition(t));
    }
    return pdaTransitions;
  }
}
