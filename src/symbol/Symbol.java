package symbol;

/**
 * <h2>Symbol</h2>
 * 
 * This class models a symbol for our
 * formal automaton.
 * 
 * Each symbol represent the minimum
 * information unit which can be 
 * represented into the input tape or
 * stack of our automata.
 * 
 * @author	Cristian Abrante
 * @version 1.0.0
 */
public class Symbol implements Comparable<Symbol> {
  
  /**
   * it stores the character string of
   * the symbol.
   */
  private String value;
  
  /**
   * This static flag is used in order to initialize
   * the empty symbol.
   */
  private static boolean emptySymbolHadBeenInitialized = false;
  
  /**
   * This is the characters sequence that represents
   * the empty symbol.
   */
  public static final String EMPTY_SYMBOL_VALUE = ".";
  
  /**
   * The empty symbol is an special kind of symbol used
   * to represent a transition with no input consume.
   */
  public static final Symbol EMPTY_SYMBOL = new Symbol(EMPTY_SYMBOL_VALUE);
  
  /**
   * Constructor of Symbol.
   * It initialize the symbol from
   * a given string
   * 
   * @param value string that represents the value.
   * @throws IllegalArgumentException if value is null,
   *         empty or the empty symbol value.
   */
  public Symbol(String value) {
    setValue(value);
  }
  
  /**
   * Method that gives an unique
   * value to each symbol.
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }
  
  /**
   * Method used to compare two symbols.
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(Symbol o) {
    return toString().compareTo(o.toString());
  }
  
  /**
   * Method used to test the equality
   * of two symbols.
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
    Symbol other = (Symbol) obj;
    if (value == null) {
      if (other.value != null)
        return false;
    } else if (!value.equals(other.value))
      return false;
    return true;
  }
  
  /**
   * returns the value of the symbol
   * as a string representation.
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return value.equals(EMPTY_SYMBOL_VALUE) ? "ε" : value;
  }

  /**
   * Sets the value of the symbol to
   * the given string.
   * 
   * @param value the value to set
   */
  private void setValue(String value) {
    if (value == null || value.isEmpty())
      throw new IllegalArgumentException("symbol value can not be null or empty");
    
    if (Symbol.EMPTY_SYMBOL_VALUE.equals(value)) {
      if (!Symbol.emptySymbolHadBeenInitialized) {
        Symbol.emptySymbolHadBeenInitialized = true;
      } else {
        String errorMsg = "trying to set the empty symbol value: " 
                         + Symbol.EMPTY_SYMBOL_VALUE;
        throw new IllegalArgumentException(errorMsg);
      }
    }
    
    this.value = value;
  }
}
