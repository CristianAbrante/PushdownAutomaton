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

import java.util.Collections;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * <h2>PushdownAutomaton</h2>
 *
 * Simulator of a pushdown
 * automaton.
 *
 * It contains the main elements
 * of this formal computing system.
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

  public boolean isPrintable;
  private int tapeSize;

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
    tapeSize = inputTape.size();
    currentEvaluationState = initialState;
    currentEvaluationTape = inputTape;
    currentEvaluationStack = new PDAStack();
    currentEvaluationStack.push(initialStackSymbol);

    evaluationStack = new EvaluationStack();

    TreeSet<PDATransition> nextTransitions =
            transitionFunction.getNextState(currentEvaluationState,
                    currentEvaluationTape.read(),
                    currentEvaluationStack.peek());
    if (isPrintable) {
      printHeader();
      printCurrentState(null, nextTransitions);
    }
    if (nextTransitions != null) {
      evaluationStack.push(currentEvaluationState,
                           currentEvaluationTape,
                           currentEvaluationStack);
      for (PDATransition t : nextTransitions) {
        if (evaluateTransition(t)) {
          if (isPrintable)
            printCurrentState(t, null);
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
      if (isPrintable)
        printCurrentState(t,null);
      return false;
    }

    TreeSet<PDATransition> nextTransitions =
            transitionFunction.getNextState(currentEvaluationState,
                                            currentEvaluationTape.read(),
                                            currentEvaluationStack.peek());
    if (isPrintable)
      printCurrentState(t, nextTransitions);
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

  private String getFormat() {
    return "| %-25s | %-5s | %-" + (tapeSize * 2 + 1) + "s | %-15s | ";
  }

  private void printCurrentState(Transition t,TreeSet<PDATransition> transitions) {
    String printFormat = getFormat();
    String currentState = String.format(printFormat,
            t == null ? "-" : t.toString(),
            currentEvaluationState,
            currentEvaluationTape.toString(),
            currentEvaluationStack.toString());
    if (transitions != null) {
      int index = 0;
      for (PDATransition transition : transitions) {
        currentState += transition + "  ";
      }
    } else {
      currentState += evaluateIfCurrentStateIsAcceptance() ?
              "ω ∈ L" : "ω ∉ L";
    }

    System.out.println(currentState);
  }

  private void printHeader() {
    String format = getFormat();
    int n = 69 + tapeSize * 2;

    String header = String.join("", Collections.nCopies(n, "-")) + "\n";
    header += String.format(getFormat(), "used transition", "state", "word (ω)", "stack");
    header += "transitions\n";
    header += String.join("", Collections.nCopies(n, "-"));
    System.out.println(header);
  }
}
