package pushdown;

import alphabet.Alphabet;
import pushdown.transition.PDATransitionFunction;
import state.SetOfStates;
import state.State;
import symbol.Symbol;

/**
 * <h2>PushdownAutomaton</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class PushdownAutomaton {

  private SetOfStates setOfStates;
  private Alphabet inputAlphabet;
  private Alphabet stackAlphabet;
  private State initialState;
  private Symbol initialStackSymbol;
  private SetOfStates acceptingStates;
  private PDATransitionFunction transitionFunction;

  public PushdownAutomaton(SetOfStates setOfStates,
                           Alphabet inputAlphabet,
                           Alphabet stackAlphabet,
                           State initialState,
                           Symbol initialStackSymbol,
                           SetOfStates acceptingStates,
                           PDATransitionFunction transitionFunction) {
    this.setOfStates = setOfStates;
    this.inputAlphabet = inputAlphabet;
    this.stackAlphabet = stackAlphabet;
    this.initialState = initialState;
    this.initialStackSymbol = initialStackSymbol;
    this.acceptingStates = acceptingStates;
    this.transitionFunction = transitionFunction;
  }

  @Override
  public String toString() {

    return  "Q = " + setOfStates + "\n" +
            "Σ = " + inputAlphabet + "\n" +
            "τ = " + stackAlphabet + "\n" +
            "s = " + initialState + "\n" +
            "z = " + initialStackSymbol + "\n" +
            "F = " + acceptingStates + "\n" +
            "δ : \n" + transitionFunction;
  }
}
