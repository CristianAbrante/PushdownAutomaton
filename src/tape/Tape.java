package tape;

import alphabet.Alphabet;
import symbol.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Tape</h2>
 * This class models
 * an input / output tape
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class Tape {

  private Alphabet alphabet;
  private List<Symbol> symbols;
  private int currentPosition;

  public Tape(Tape that) {
    List<Symbol> clonedSymbols = new ArrayList<>();
    for (Symbol symbol : that.getSymbols()) {
      clonedSymbols.add(symbol);
    }
    this.setAlphabet(that.getAlphabet());
    this.setSymbols(clonedSymbols);
    this.setCurrentPosition(that.getCurrentPosition());
  }

  public Tape(List<Symbol> symbols, Alphabet alphabet) {
    this.alphabet = alphabet;
    setSymbols(symbols);
  }

  public Symbol read() {
    if (currentPosition >= 0
            && currentPosition < getSymbols().size()) {
      return getSymbols().get(currentPosition);
    }
    return Symbol.EMPTY_SYMBOL;
  }

  public void write(Symbol symbol) {
    if (symbol == null)
      throw new NullPointerException("symbol can not be null.");

    getSymbols().add(currentPosition, symbol);
  }

  public void move(Movement movement) {
    currentPosition += movement.value;
  }

  public void moveLeft() {
    move(Movement.LEFT);
  }

  public void moveRight() {
    move(Movement.RIGHT);
  }

  public boolean isReseted() {
    return currentPosition == 0;
  }

  public Alphabet getAlphabet() {
    return alphabet;
  }

  public void setAlphabet(Alphabet alphabet) {
    this.alphabet = alphabet;
  }

  public int getCurrentPosition() {
    return currentPosition;
  }

  public void setCurrentPosition(int currentPosition) {
    this.currentPosition = currentPosition;
  }

  public boolean hasReachedTheEnd() {
    return getCurrentPosition() >= getSymbols().size();
  }

  private List<Symbol> getSymbols() {
    return symbols;
  }

  private void setSymbols(List<Symbol> symbols) {
    if (symbols == null)
      throw new NullPointerException("symbols can not be null.");

    for (Symbol symbol : symbols)
      if (symbol == null || !alphabet.containsByValue(symbol.toString()))
        throw new NullPointerException("A symbol is null");

    this.symbols = symbols;
  }

  @Override
  public String toString() {
    String tape = "";
    for (int i = getCurrentPosition(); i < getSymbols().size(); i++) {
      tape += getSymbols().get(i) + " ";
    }
    return tape;
  }
}
