package tape.reader;

import symbol.Symbol;
import tape.Tape;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * <h2>TapeReader</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class TapeReader {
  String input;
  List<Symbol> tokenizedInput;

  public TapeReader(FileReader file) {
    if (file == null)
      throw new NullPointerException("file can not be null.");

    String input = (new Scanner(file)).useDelimiter("\\Z").next();
    setInput(input);
  }

  public TapeReader(String input) {
    setInput(input);
  }

  public void setInput(String input) {
    if (input == null)
      throw new NullPointerException("input can not be null.");

    tokenizedInput = tokenizeString(input);
  }

  public String getInput() {
    return input;
  }

  public List<Symbol> getTokenizedInput() {
    return tokenizedInput;
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
  private List<Symbol> tokenizeString(String strToTokenize) {
    List<String> splittedList = Arrays.asList(strToTokenize.split("\\s+"));
    List<Symbol> returnList = new ArrayList<>();
    for (String token : splittedList) {
      if (!token.isEmpty())
        returnList.add(new Symbol(token));
    }
    return returnList;
  }
}
