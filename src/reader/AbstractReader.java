package reader;

import state.State;
import symbol.Symbol;
import transition.Transition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h2>Abstract Reader</h2>
 *
 * This is an abstract reader
 * class whose aim is to read
 * from a string the most common
 * computational machine elements,
 * in a certain format.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public abstract class AbstractReader {

  /**
   * This symbol is used to delimit
   * comments into the specified string.
   */
  public final String COMMENT_DELIMITER = "#";

  /**
   * Read a list of symbols from the
   * specified string. The format of
   * the symbols in the string is
   * like this:
   *
   * S1 S2 ... Sn
   *
   * @param symbolsString the string contained the identifiers.
   * @return a list of symbols.
   */
  public List<Symbol> readSymbols(String symbolsString) {
    String noCommentsStr = deleteComments(symbolsString);
    List<String> tokenizedString = tokenizeString(noCommentsStr);
    List<Symbol> symbols = new ArrayList<>();
    for (String token : tokenizedString) {
      symbols.add(new Symbol(token));
    }
    return symbols;
  }

  /**
   * Read a list of state identifiers from
   * the specified string. The format of
   * the states in the string is like
   * this:
   *
   * q0 q1 q2 ... qn
   *
   * @param statesString string containing the states id.
   * @return a list with the states.
   */
  public List<State> readStates(String statesString) {
    String noCommentsStr = deleteComments(statesString);
    List<String> tokenizedString = tokenizeString(statesString);
    List<State> states = new ArrayList<>();
    for (String token : tokenizedString) {
      states.add(new State(token));
    }
    return states;
  }

  /**
   * Reads a transition of the machine
   * we are modelling a returns as a
   * transition object.
   *
   * The format of the transition
   * depends on the machine we are
   * modelling.
   *
   * @param transitionString definition of the transition.
   * @return transition object.
   */
  public abstract Transition readTransition(String transitionString);

  /**
   * Deletes the line comments
   * from the specified string,
   * and returns the non-comment
   * string.
   *
   * @param commentsString commented string.
   * @return uncomented part of the string.
   */
  protected String deleteComments(String commentsString) {
    List<String> splittedList = Arrays.asList(commentsString.split(COMMENT_DELIMITER));
    return splittedList.get(0);
  }

  /**
   * Return the string as a list
   * of tokens. We consider the
   * tokens splitted by spaces
   * or other space delimitations.
   *
   * @param strToTokenize string that we want to tokenize.
   * @return tokens of the string.
   */
  protected List<String> tokenizeString(String strToTokenize) {
    List<String> splittedList = Arrays.asList(strToTokenize.split("\\s+"));
    List<String> returnList = new ArrayList<String>();
    for (String token : splittedList) {
      if (!token.isEmpty())
        returnList.add(token);
    }
    return returnList;
  }
}
