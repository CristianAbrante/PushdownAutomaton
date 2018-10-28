package reader;

import state.State;
import symbol.Symbol;
import transition.Transition;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
  public final static String COMMENT_DELIMITER = "#";

  /**
   * Variable used to store the splitted
   * definition of the machine.
   */
  private ArrayList<String> splittedDefinition;

  /**
   * Index that determines which line
   * of the definition we are examining.
   */
  private int currentDefinitionIndex = 0;

  /**
   * Constructor of the abstract reader.
   *
   * It takes an input reader file as
   * a parameter.
   *
   * @param file that we want to read from.
   */
  public AbstractReader(FileReader file) {
    if (file == null)
      throw new NullPointerException("file can not be null.");

    String definition = (new Scanner(file)).useDelimiter("\\Z").next();
    setDefinition(definition);
  }

  /**
   * Constructor of the abstract reader.
   *
   * It takes a string which contains a
   * definition of the machine.
   *
   * @param definition of the machine.
   */
  public AbstractReader(String definition) {
    setDefinition(definition);
  }

  /**
   * returns the definition of the
   * machine without spaces and
   * comments.
   *
   * @return definition of the machine.
   */
  public String getDefinition() {
    String definition = "";
    for (String definitionLine : splittedDefinition) {
      definition += definitionLine + "\n";
    }
    return definition;
  }

  /**
   * Sets the definition of the string.
   *
   * @param definition of the machine.
   */
  public void setDefinition(String definition) {
    if (definition == null)
      throw new NullPointerException("definition can not be null.");

    splittedDefinition = new ArrayList<String>();
    String[] definitionLines = definition.split("\\r?\\n");
    for (String line : definitionLines) {
      String cleanedLine = deleteComments(line);
      if (!cleanedLine.isEmpty()) {
        splittedDefinition.add(cleanedLine);
      }
    }
    currentDefinitionIndex = 0;
  }

  /**
   * Sets the examining line of the
   * definition to the first line.
   */
  protected void resetLinePosition() {
    currentDefinitionIndex = 0;
  }

  /**
   * Returns the next line of
   * the definition.
   *
   * @return next of the definition
   *         or {@code null} if there are
   *         no more lines.
   */
  protected String getNextLine() {
    if (hasMoreLines()) {
      return splittedDefinition.get(currentDefinitionIndex++);
    } else {
      return null;
    }
  }

  /**
   * Tests if there are more lines in the
   * definition.
   *
   * @return {@code true} if there are more
   *          lines in the definition.
   */
  protected boolean hasMoreLines() {
    return currentDefinitionIndex < splittedDefinition.size();
  }

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
  protected List<Symbol> readSymbols(String symbolsString) {
    List<String> tokenizedString = tokenizeString(symbolsString);
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
  protected List<State> readStates(String statesString) {
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
  protected abstract Transition readTransition(String transitionString);

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

  /**
   * Deletes the line comments
   * from the specified string,
   * and returns the non-comment
   * string trimmed.
   *
   * @param commentsString commented string.
   * @return uncomented part of the string.
   */
  private String deleteComments(String commentsString) {
    List<String> splittedList = Arrays.asList(commentsString.split(COMMENT_DELIMITER));
    return splittedList.get(0).trim();
  }
}