/**
 * 
 */
package alphabet;

import java.util.Collection;
import java.util.TreeSet;

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
public class Alphabet {
  
  /**
   * This is the set of symbols
   * that belongs to the alphabet.
   */
  private TreeSet<Symbol> symbols;
  
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
    testIfNull(symbol);
    this.symbols = new TreeSet<Symbol>();
    add(symbol);
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
    setSymbols(symbols);
  }
  
  /**
   * Returns the number of symbols 
   * that the alphabet contains.
   * 
   * Size of the alphabet is always greater
   * than zero.
   * 
   * @return number of symbols that the
   *         alphabet contains.
   */
  public int size() {
    return getSymbols().size();
  }
  
  /**
   * Introduce the specified symbol
   * into the alphabet.
   * 
   * @param symbol symbol to be added to the set.
   * @return <code>true</code> if alphabet did not 
   *         already contained the symbol.
   * @throws NullPointerException 
   *         if symbol is <code>null</code>.
   */
  public boolean add(Symbol symbol) {
    testIfNull(symbol);
    return getSymbols().add(symbol);
  }
  
  /**
   * Removes the specified symbol from the
   * alphabet.
   * 
   * @param symbol symbol that is going to 
   *        be removed.
   * @return <code>true</code> if symbol
   *         belonged to the alphabet.
   * @throws NullPointerException 
   *         if symbol is <code>null</code>.
   */
  public boolean remove(Symbol symbol) {
    testIfNull(symbol);
    return getSymbols().remove(symbol);
  }

  /**
   * Test if alphabet contains the
   * specified symbol.
   * 
   * @param symbol symbol to be tested
   *        if it belongs to the alphabet.
   * @return <code>true</code> if the symbol
   *         belonged to the alphabet and
   *         false otherwise.
   * @throws NullPointerException 
   *         if symbol is <code>null</code>.
   */
  public boolean contains(Symbol symbol) {
    testIfNull(symbol);
    return getSymbols().contains(symbol);
  }

  /**
   * Tests if contains all the symbols
   * of the collection.
   *
   * @param symbols to be tested.
   * @return {@code true} if all elements
   *          belong to the alphabet.
   */
  public boolean containsAll(Collection<Symbol> symbols) {
    return getSymbols().containsAll(symbols);
  }

  /**
   * Tests if set contains the symbol by
   * value.
   *
   * @param value of the symbol.
   * @return {@code true} if all elements
   *         belong to the alphabet.
   */
  public boolean containsByValue(String value) {
    return getSymbols().contains(new Symbol(value));
  }

  /**
   * Tests if contains all the elements
   * by value.
   *
   * @param values that we want to test.
   * @return {@code true} if all elements
   *         belong to the alphabet.
   */
  public boolean containsAllByValue(Collection<String> values) {
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
   * Returns the symbol given the
   * value.
   *
   * @param value of the symbol
   * @return symbol if it belongs to the alphabet
   *         and {@code null} otherwise.
   */
  public Symbol getSymbol(String value) {
    if (value == null)
      throw new NullPointerException("identifier can not be null.");

    for (Symbol symbol : getSymbols()) {
      if (symbol.toString().equals(value)) {
        return symbol;
      }
    }
    return null;
  }

  /**
   * Returns the alphabet in a string
   * representation like this:
   * 
   * {a1, a2, a3, ..., an}
   * 
   * @return String representation of the 
   *          alphabet.
   */
  @Override
  public String toString() {
    String alphabet = "{";
    int index = 0;
    for (Symbol symbol : getSymbols()) {
      alphabet += index < size() - 1 ? symbol + ", " : symbol;
      index += 1;
    };
    alphabet += "}";
    return alphabet;
  }

  /**
   * Method that test if two 
   * alphabets are equals.
   * 
   * Two alphabets are equals if both
   * contains the same number of elements
   * and all of them are equal.
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Alphabet other = (Alphabet) obj;
    if (symbols == null) {
      if (other.symbols != null)
        return false;
    } else if (!symbols.equals(other.symbols))
      return false;
    return true;
  }


  /**
   * getter for alphabet symbols.
   * 
   * @return symbols of the alphabet.
   */
  private TreeSet<Symbol> getSymbols() {
    return symbols;
  }

  /**
   * setter for alphabet symbols.
   * 
   * @param symbols the symbols to set
   * @throws NullPointerException if symbols is not <code>null</code>.
   *         llegalArgumentException if symbols is an empty collection.
   */
  private void setSymbols(Collection<Symbol> symbols) {
    if (symbols == null)
      throw new NullPointerException("set of symbols can not be null");
      
    if (symbols.isEmpty())
      throw new IllegalArgumentException("alphabet can not be empty.");
    
    this.symbols = new TreeSet<Symbol>();
    for (Symbol symbol : symbols) {
      add(symbol);
    }
  }
  
  /**
   * Supply method for testing is a symbol is
   * null.
   * 
   * @param symbol to be tested.
   * @throws NullPointerException if symbol
   *         is actually <code>null</code>.
   */
  private void testIfNull(Symbol symbol) {
    if (symbol == null) {
      throw new NullPointerException("Symbol can not be null.");
    }
  }
}
