package tape;

import symbol.Symbol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <h2>Tape</h2>
 *
 * This class is used to
 * model a input/output tape.
 *
 * This tape is used for various
 * automatons for giving input
 * and results of computations
 * respectively.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class Tape {

  /**
   * List of symbols that constitute
   * the tape.
   */
  private List<Symbol> symbols;

  /**
   * Head of the i/o tape.
   * By default it is positioned
   * on the first symbol.
   */
  private int head;

  /**
   * Constructor of the class.
   * it creates an empty tape.
   */
  public Tape() {
    symbols = new ArrayList<>();
  }

  /**
   * Constructor of the class.
   *
   * It creates a tape with only
   * one symbol.
   *
   * @param symbol that constains
   *               the tape.
   * @throws NullPointerException if
   *          symbol is {@code null}.
   */
  public Tape(Symbol symbol) {
    this();
    if (symbol == null)
      throw new NullPointerException("symbol can not be null.");

    head = 0;
    write(symbol);
  }

  /**
   * Constructor of the class.
   *
   * It creates a tae that contains
   * the collection of symbols.
   *
   * @param symbols that tape is going
   *                to contain.
   */
  public Tape(Collection<Symbol> symbols) {
    setSymbols(symbols);
  }

  /**
   * Constructor of the class.
   *
   * It creates a copy of the
   * parameter tape.
   *
   * @param that tape that we want to
   *             copy.
   * @throws NullPointerException if tape
   *          that we are copying is {@code null}.
   */
  public Tape(Tape that) {
    this();
    if (that == null)
      throw new NullPointerException("that can not be null.");

    for (Symbol symbol : that.getSymbols()) {
      getSymbols().add(symbol);
    }
    head = that.getHeadOffset();
  }

  /**
   * Reads the symbol in the
   * head position.
   *
   * @return the symbol placed
   * at head.
   */
  public Symbol read() {
    return readAt(head);
  }

  /**
   * Writes the specified
   * symbol in the head position.
   *
   * If head is behind the initial
   * position it fills with empty symbols.
   *
   * @param symbol we want to write.
   */
  public void write(Symbol symbol) {
    if (symbol == null)
      throw new NullPointerException("symbol can not be null");

    if (isInBounds(head)) {
      getSymbols().add(head, symbol);
      getSymbols().remove(head + 1);
    } else {
      if (head < 0) {
        for (int i = head + 1; i < 0; i++) {
          getSymbols().add(0, Symbol.EMPTY_SYMBOL);
        }
        getSymbols().add(0, symbol);
        head = 0;
      } else {
        for (int i = size(); i < head; i++) {
          getSymbols().add(Symbol.EMPTY_SYMBOL);
        }
        getSymbols().add(symbol);
      }
    }
  }

  /**
   * Move the tape on the specified
   * movement.
   *
   * @param movement we want to move
   *                 the tape to.
   */
  public void move(Movement movement) {
    head += movement.value;
  }

  /**
   * Moves the head to the left.
   */
  public void moveLeft() {
    move(Movement.LEFT);
  }

  /**
   * Moves the head to the right.
   */
  public void moveRight() {
    move(Movement.RIGHT);
  }

  /**
   * Stops the tape.
   */
  public void stop() {
    move(Movement.STOP);
  }

  /**
   * Returns the size of
   * the symbols of the tape.
   *
   * @return size of the symbols
   *          of the tape.
   */
  public int size() {
    return getSymbols().size();
  }

  /**
   * put the head of the tape
   * to the beginning.
   */
  public void reset() {
    head = 0;
  }

  /**
   * Returns the offset of
   * the head from the beginning.
   *
   * @return offset of the head.
   */
  public int getHeadOffset() {
    return head;
  }

  /**
   * Returns the symbols of the
   * tape.
   *
   * @return symbols of the tape.
   */
  private List<Symbol> getSymbols() {
    return symbols;
  }

  /**
   * Sets the symbols of the tape
   * to the specified collection.
   *
   * @param symbols of the tape.
   */
  public void setSymbols(Collection<Symbol> symbols) {
    symbols = new ArrayList<>();
    getSymbols().addAll(symbols);
    head = 0;
  }

  /**
   * Converts the tape to a string.
   *
   * @return the tape as a string
   *          representation.
   */
  @Override
  public String toString() {
    String tape = "";
    for (int i = head; i < size(); i++) {
      tape += readAt(i).toString() + " ";
    }
    tape += "$";
    return tape;
  }

  /**
   * Check if index is in bounds
   *
   * @param index we want to check.
   * @return
   */
  private boolean isInBounds(int index) {
    return index >= 0 && index < getSymbols().size();
  }

  /**
   * Reads the symbol at specified
   * position.
   *
   * @param i position where we want to
   *          read.
   * @return symbol if i is in bounds
   *          and empty symbol otherwise.
   */
  private Symbol readAt(int i) {
    if (isInBounds(i)) {
      return getSymbols().get(i);
    } else {
      return Symbol.EMPTY_SYMBOL;
    }
  }
}
