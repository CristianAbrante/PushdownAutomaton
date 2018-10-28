package alphabet;

import java.util.Collection;

import ComputationalSet.ComputationalSet;
import symbol.Symbol;

/**
 * <h2>Alphabet</h2>
 * 
 * Formally, an alphabet is a non empty
 * and finite set of symbols.
 * 
 * A = {s1, s2, ..., sn}
 * 
 * Alphabet determines which symbols
 * belongs to a certain formal 
 * language.
 * 
 * Alphabet is used by many formal 
 * computation systems such as DFA,
 * NFA, PDA or Turing machine
 * 
 * @author	Cristian Abrante
 * @version 1.0.0
 */
public class Alphabet
        extends ComputationalSet<Symbol> {
  
  /**
   * Constructor of Alphabet.
   * 
   * It takes at least one symbol,
   * because alphabets can't be 
   * empty.
   * 
   * @param symbol that is going
   *        to belong to the alphabet.
   * @throws NullPointerException 
   *         if symbol is <code>null</code>.
   */
  public Alphabet(Symbol symbol) {
    super(symbol);
  }
  
  /**
   * Constructor of Alphabet.
   * 
   * It takes a collection of symbols
   * which are going to be introduced
   * in the alphabet.
   * 
   * Repeated symbols are only going to
   * be introduced once, thus alphabet
   * is a set.
   * 
   * @param symbols that belongs to the
   *        alphabet.
   * @throws NullPointerException 
   *         if symbols is <code>null</code>.
   */
  public Alphabet(Collection<Symbol> symbols) {
    super(symbols);
  }

  /**
   * Tests if set contains the symbol by
   * value.
   *
   * @param value of the symbol.
   * @return {@code true} if a symbol
   *          with specified symbol
   *          belongs to the alphabet.
   */
  public boolean containsByValue(String value) {
    return super.contains(new Symbol(value));
  }

  /**
   * Tests if contains all the elements
   * by value.
   *
   * @param values that we want to test.
   * @return {@code true} if all elements
   *         belong to the alphabet.
   * @throws NullPointerException if
   *          values or any element inside are
   *          {@code null}.
   */
  public boolean containsAllByValue(
          Collection<String> values) {
    if (values == null)
      throw new NullPointerException("values can not be null.");

    for (String value : values) {
      if (!containsByValue(value)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Method that returns the
   * equal symbol on the set
   * if present.
   *
   * @param symbol element to be checked.
   * @return the sumbol if it is present
   *          and {@code null} otherwise.
   * @throws NullPointerException if symbol
   *          is {@code null}.
   */
  @Override
  public Symbol get(Symbol symbol) {
    if (symbol != null
            && symbol.equals(Symbol.EMPTY_SYMBOL)) {
      return Symbol.EMPTY_SYMBOL;
    }
    return super.get(symbol);
  }

  /**
   * Returns the symbol given the
   * value.
   *
   * @param value of the symbol
   * @return symbol if it belongs to the alphabet
   *          or the empty symbol if the value is
   *          the empty symbol value. And
   *          {@code null} otherwise.
   */
  public Symbol getByValue(String value) {
    if (value.equals(Symbol.EMPTY_SYMBOL_VALUE))
      return Symbol.EMPTY_SYMBOL;

    return get(new Symbol(value));
  }
}
