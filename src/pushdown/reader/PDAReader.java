package pushdown.reader;

import alphabet.Alphabet;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import pushdown.PushdownAutomaton;
import pushdown.transition.PDATransition;
import pushdown.transition.PDATransitionFunction;
import reader.AbstractReader;
import state.SetOfStates;
import state.State;
import symbol.Symbol;
import transition.SymbolList;
import transition.Transition;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>PDAReader</h2>
 *
 * This class is used to read
 * a pushdown automaton from
 * a file with a specific format.
 *
 * The format of the PDA files is
 * like this:
 *
 * q0 q1 ... qi # list of states.
 * a1 a2 ... aj # list symbols of the input alphabet.
 * A1 A2 ... Ak # list symbols of the stack alphabet.
 * q0           # initial state.
 * A0           # initial stack symbol.
 * f0 f1 ... fr # list of accepting states.
 *
 * q a A q' A'1 A'2 ... # transition 1 (q, a, A) -> (q', {A'1, A'2, ...})
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class PDAReader extends AbstractReader {

  /**
   * Some variables used to store
   * the elements of the pushdown
   * automaton.
   */
  private SetOfStates setOfStates;
  private Alphabet inputAlphabet;
  private Alphabet stackAlphabet;
  private State initialState;
  private Symbol initialStackSymbol;
  private SetOfStates acceptingStates;
  private PDATransitionFunction transitionFunction;

  /**
   * Readed automaton.
   */
  private PushdownAutomaton PDA;

  public PDAReader(FileReader file) {
    super(file);
  }

  public PDAReader(String definition) {
    super(definition);
  }

  public PushdownAutomaton getReadPDA() {
    return PDA;
  }

  @Override
  public void setDefinition(String definition) {
    super.setDefinition(definition);

    String currentLine;

    // line 0 contains the set of states.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the set of states.");

    setOfStates = new SetOfStates(super.readStates(currentLine));

    // line 1 contains the input alphabet.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the input alphabet.");

    inputAlphabet = new Alphabet(super.readSymbols(currentLine));

    // line 2 contains the stack alphabet.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the stack alphabet.");

    stackAlphabet = new Alphabet(super.readSymbols(currentLine));

    // line 3 contains the initial state.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the initial state.");

    initialState = getInitialState(currentLine);

    // line 4 contains the initial stack symbol.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the initial stack symbol.");

    initialStackSymbol = getInitialStackSymbol(currentLine);

    // line 5 contains the acceptance states.
    currentLine = getNextLine();
    if (currentLine == null)
      throw new IllegalArgumentException("expected definition of the initial stack symbol.");

    acceptingStates = new SetOfStates(getAcceptingStates(currentLine));

    // line 6 and though are used to specify the transitions.
    List<Transition> transitions = new ArrayList<>();
    while (hasMoreLines()) {
      currentLine = getNextLine();
      Transition t = readTransition(currentLine);
      transitions.add(t);
    }
    transitionFunction = new PDATransitionFunction(transitions);

    // Creation of the PDA.
    PDA = new PushdownAutomaton(setOfStates,
            inputAlphabet,
            stackAlphabet,
            initialState,
            initialStackSymbol,
            acceptingStates,
            transitionFunction);
  }

  @Override
  protected Transition readTransition(String transitionString) {
    List<String> tokenizedString = tokenizeString(transitionString);
    if (tokenizedString.size() < 5)
      throw new IllegalArgumentException("transition must have at least 5 elements");

    State initialState = setOfStates.getById(tokenizedString.get(0));
    if (initialState == null)
      throw new IllegalArgumentException("invalid initial state on transition: " + transitionString);

    Symbol inputSymbol = inputAlphabet.getByValue(tokenizedString.get(1));
    if (inputSymbol == null)
      throw new IllegalArgumentException("invalid input symbol on transition: " + transitionString);

    Symbol stackSymbol = stackAlphabet.getByValue(tokenizedString.get(2));
    if (stackSymbol == null)
      throw new IllegalArgumentException("invalid stack symbol on transition: " + transitionString);

    State nextState = setOfStates.getById(tokenizedString.get(3));
    if (nextState == null)
      throw new IllegalArgumentException("invalid next state on transition: " + transitionString);

    SymbolList nextSymbolStack = new SymbolList();
    for (int i = 4; i < tokenizedString.size(); ++i) {
      nextSymbolStack.add(stackAlphabet.getByValue(tokenizedString.get(i)));
    }

    Triplet<State, Symbol, Symbol> initialStateTriplet = new Triplet<>(initialState, inputSymbol, stackSymbol);
    Pair<State, SymbolList> nextStatePair = new Pair<>(nextState, nextSymbolStack);
    return new PDATransition(initialStateTriplet, nextStatePair);
  }

  private State getInitialState(String currentLine) {
    List<String> tokenizedString = tokenizeString(currentLine);
    if (tokenizedString.size() != 1)
      throw new IllegalArgumentException("expected only one initial state.");

    return setOfStates.getById(tokenizedString.get(0));
  }

  private Symbol getInitialStackSymbol(String currentLine) {
    List<String> tokenizedString = tokenizeString(currentLine);
    if (tokenizedString.size() != 1)
      throw new IllegalArgumentException("expected only one initial stack symbol.");

    return stackAlphabet.getByValue(tokenizedString.get(0));
  }

  private List<State> getAcceptingStates(String currentLine) {
    List<String> tokenizedString = tokenizeString(currentLine);
    List<State> states = new ArrayList<>();
    for (String token : tokenizedString) {
      State acceptingState = setOfStates.getById(token);
      if (acceptingState != null) {
        states.add(acceptingState);
      } else {
        throw new IllegalArgumentException("accepting state " + token + " is not defined on the set of states");
      }
    }
    return states;
  }
}
