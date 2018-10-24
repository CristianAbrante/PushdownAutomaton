package pushdown;

import alphabet.Alphabet;
import org.javatuples.Triplet;
import pushdown.stack.PDAStack;
import pushdown.transition.PDATransition;
import pushdown.transition.PDATransitionFunction;
import state.SetOfStates;
import state.State;
import symbol.Symbol;
import tape.Tape;
import transition.Transition;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * <h2>PushdownAutomaton</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class PushdownAutomaton {

  private class EvaluationStack
          extends Stack<Triplet<State, Tape, PDAStack>> {
    public void push(State state,
                     Tape tape,
                     PDAStack stack) {
      Tape copyTape = new Tape(tape);
      PDAStack copyStack = new PDAStack(stack);
      Triplet<State, Tape, PDAStack> evaluationState =
              new Triplet<>(state, copyTape, copyStack);
      super.push(evaluationState);
    }

    public Triplet<State, Tape, PDAStack> pop() {
      return super.pop();
    }

    public State peekState() {
      return peek().getValue0();
    }

    public Tape peekTape() {
      return peek().getValue1();
    }

    public PDAStack peekStack() {
      return peek().getValue2();
    }
  }

  private SetOfStates setOfStates;
  private Alphabet inputAlphabet;
  private Alphabet stackAlphabet;
  private State initialState;
  private Symbol initialStackSymbol;
  private SetOfStates acceptingStates;
  private PDATransitionFunction transitionFunction;

  private State currentEvaluationState;
  private Tape currentEvaluationTape;
  private PDAStack currentEvaluationStack;
  private EvaluationStack evaluationStack;

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

  public SetOfStates getSetOfStates() {
    return setOfStates;
  }

  public Alphabet getInputAlphabet() {
    return inputAlphabet;
  }

  public Alphabet getStackAlphabet() {
    return stackAlphabet;
  }

  public State getInitialState() {
    return initialState;
  }

  public Symbol getInitialStackSymbol() {
    return initialStackSymbol;
  }

  public SetOfStates getAcceptingStates() {
    return acceptingStates;
  }

  public PDATransitionFunction getTransitionFunction() {
    return transitionFunction;
  }

  public boolean evaluate(Tape inputTape) {
    if (inputTape == null)
      throw new NullPointerException("can not evaluate a null input tape.");

    if (!inputTape.isReseted())
      throw new IllegalArgumentException("tape must be reset");

    currentEvaluationState = initialState;
    currentEvaluationTape = inputTape;
    currentEvaluationStack = new PDAStack();
    currentEvaluationStack.push(initialStackSymbol);

    evaluationStack = new EvaluationStack();

    TreeSet<PDATransition> nextTransitions =
            transitionFunction.getNextState(currentEvaluationState,
                    currentEvaluationTape.read(),
                    currentEvaluationStack.peek());
    printCurrentState(nextTransitions);
    if (nextTransitions != null) {
      evaluationStack.push(currentEvaluationState,
                           currentEvaluationTape,
                           currentEvaluationStack);
      for (PDATransition t : nextTransitions) {
        if (evaluateTransition(t)) {
          return true;
        } else {
          restoreActualState();
        }
      }
      evaluationStack.pop();
    }
    return false;
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

  private boolean evaluateTransition(PDATransition t) {
    //TODO: Check if states are equals as transition.

    currentEvaluationState = t.getNextStateNode();
    if (!t.getCurrentStateSymbol().equals(Symbol.EMPTY_SYMBOL)) {
      currentEvaluationTape.moveRight();
    }
    currentEvaluationStack.pop();
    currentEvaluationStack.push(t.getNextStateStackSymbols());
    if (evaluateIfCurrentStateIsAcceptance())
      return true;
    if (currentEvaluationStack.isEmpty()) {
      printCurrentState(null);
      return false;
    }

    TreeSet<PDATransition> nextTransitions =
            transitionFunction.getNextState(currentEvaluationState,
                                            currentEvaluationTape.read(),
                                            currentEvaluationStack.peek());
    printCurrentState(nextTransitions);
    if (nextTransitions != null) {
      evaluationStack.push(currentEvaluationState,
                           currentEvaluationTape,
                           currentEvaluationStack);
      for (PDATransition transition : nextTransitions) {
        if (evaluateTransition(transition)) {
          return true;
        } else {
          restoreActualState();
        }
      }
      evaluationStack.pop();
      return false;
    } else {
      return evaluateIfCurrentStateIsAcceptance();
    }
  }

  private void restoreActualState() {
    currentEvaluationState = new State(evaluationStack.peekState());
    currentEvaluationTape = new Tape(evaluationStack.peekTape());
    currentEvaluationStack = new PDAStack(evaluationStack.peekStack());
  }

  private boolean evaluateIfCurrentStateIsAcceptance() {
    if (currentEvaluationTape.hasReachedTheEnd()) {
      return acceptingStates.contains(currentEvaluationState)
              || currentEvaluationStack.isEmpty();
    }
    return false;
  }

  private void printCurrentState(TreeSet<PDATransition> transitions) {
    String currentState = "";

    currentState += currentEvaluationState + "  " +
            currentEvaluationTape.toString() + "  " +
            currentEvaluationStack.toString() + "  ";
    if (transitions != null) {
      for (PDATransition t : transitions) {
        currentState += t + " | ";
      }
    } else {
      currentState += "w ne L";
    }

    System.out.println(currentState);
  }
}
