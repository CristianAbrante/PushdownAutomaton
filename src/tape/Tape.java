package tape;

import alphabet.Alphabet;
import symbol.Symbol;

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

  public Tape(List<Symbol> symbols, Alphabet alphabet) {
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

  private List<Symbol> getSymbols() {
    return symbols;
  }

  private void setSymbols(List<Symbol> symbols) {
    if (symbols == null)
      throw new NullPointerException("symbols can not be null.");

    for (Symbol symbol : symbols)
      if (symbol == null || !alphabet.contains(symbol))
        throw new NullPointerException("A symbol is null");

    this.symbols = symbols;
  }
}
