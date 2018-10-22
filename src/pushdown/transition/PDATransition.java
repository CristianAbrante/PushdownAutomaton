package pushdown.transition;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Tuple;
import state.State;
import symbol.Symbol;
import transition.SymbolList;
import transition.Transition;

/**
 * <h2>PDATransition</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class PDATransition extends Transition {

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
  public PDATransition(Triplet<State, Symbol, Symbol> currentState,
                       Pair<State, SymbolList> nextState) {
    super(currentState, nextState);
  }

  /**
   * Constructor of the class.
   * <p>
   * It takes the parameters of
   * a pushdown automaton transition.
   * </p>
   *
   * @param currentState
   * @param currentTapeSymbol
   * @param currentStackSymbol
   * @param nextState
   * @param nextStackSymbols
   */
  public PDATransition(State currentState,
                       Symbol currentTapeSymbol,
                       Symbol currentStackSymbol,
                       State nextState,
                       SymbolList nextStackSymbols) {
    this(new Triplet<>(currentState, currentTapeSymbol, currentStackSymbol),
            new Pair<>(nextState, nextStackSymbols));
  }

  /**
   * Sets the current state of
   * the transition.
   *
   * @param currentState
   * @param currentTapeSymbol
   * @param currentStackSymbol
   */
  public void setCurrentState(State currentState,
                              Symbol currentTapeSymbol,
                              Symbol currentStackSymbol) {
    super.setCurrentState(new Triplet<>(currentState, currentTapeSymbol, currentStackSymbol));
  }

  /**
   * Sets next state of the
   * transition.
   *
   * @param nextState
   * @param nextStackSymbols
   */
  public void setNextState(State nextState,
                           SymbolList nextStackSymbols) {
    super.setNextState(new Pair<>(nextState, nextStackSymbols));
  }
}
