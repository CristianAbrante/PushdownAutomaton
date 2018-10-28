/**
 * 
 */
package alphabet;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import symbol.Symbol;

/**
 * <h2>AlphabetTest</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		17/10/2018
 * @version 1.0.0
 */
public class AlphabetTest {

  @Test
  public void simpleConstructionTest() {
    Symbol x = new Symbol("x");
    Symbol y = new Symbol("y");
    
    Alphabet sigma = new Alphabet(x);
    assertEquals(1, sigma.size());
    
    sigma = new Alphabet(y);
    assertEquals(1, sigma.size());
  }
  
  @Test(expected = NullPointerException.class)
  public void simpleConstructorErrorTest() {
    Symbol s = null;
    new Alphabet(s);
  }
  
  @Test
  public void multipleInitializationTest() {
    Symbol x = new Symbol("x");
    Symbol y = new Symbol("y");
    Symbol z = new Symbol("z");
    
    // Tree set initialization.
    Collection<Symbol> symbols = new TreeSet<Symbol>();
    symbols.add(x);
    symbols.add(y);
    symbols.add(z);
    
    Alphabet sigma = new Alphabet(symbols);
    assertEquals(3, sigma.size());
    
    // Array Lisr
    symbols = new ArrayList<Symbol>();
    symbols.add(x);
    symbols.add(y);
    symbols.add(z);
    symbols.add(x);
    symbols.add(y);
    symbols.add(z);
    
    sigma = new Alphabet(symbols);
    assertEquals(3, sigma.size());
  }
  
  @Test
  public void containsTest() {
    Symbol x = new Symbol("x");
    Symbol y = new Symbol("y");
    Symbol z = new Symbol("z");
    
    // Tree set initialization.
    Collection<Symbol> symbols = new TreeSet<Symbol>();
    symbols.add(x);
    symbols.add(y);
    symbols.add(z);
    
    Alphabet sigma = new Alphabet(symbols);
    
    assertTrue(sigma.contains(x));
    assertTrue(sigma.contains(y));
    assertTrue(sigma.contains(z));
    
    assertFalse(sigma.contains(new Symbol("i")));
    assertFalse(sigma.contains(Symbol.EMPTY_SYMBOL));
  }
  
  @Test
  public void addTest() {
    Symbol x = new Symbol("x");
    Symbol y = new Symbol("y");
    Symbol z = new Symbol("z");
    
    // Tree set initialization.
    Collection<Symbol> symbols = new TreeSet<Symbol>();
    symbols.add(x);
    symbols.add(y);
    symbols.add(z);
    
    Alphabet sigma = new Alphabet(symbols);
    Symbol i = new Symbol("i");
    assertFalse(sigma.contains(i));
    sigma.add(i);
    assertTrue(sigma.contains(i));
    
    sigma.add(i);
    sigma.add(i);
    sigma.add(i);
    sigma.add(i);
    assertEquals(4, sigma.size());
  }
  
  @Test
  public void removeTest() {
    Symbol x = new Symbol("x");
    Symbol y = new Symbol("y");
    Symbol z = new Symbol("z");
    Symbol i = new Symbol("i");
    
    // Tree set initialization.
    Collection<Symbol> symbols = new TreeSet<Symbol>();
    symbols.add(x);
    symbols.add(y);
    symbols.add(z);
    
    Alphabet sigma = new Alphabet(symbols);
    sigma.remove(y);
    assertFalse(sigma.contains(y));
    sigma.remove(i);
    assertFalse(sigma.contains(i));
    assertEquals(2, sigma.size());
  }
  
  @Test
  public void toStringTest() {
    Symbol x = new Symbol("x");
    Symbol y = new Symbol("y");
    Symbol z = new Symbol("z");
    Symbol i = new Symbol("i");
    
    // Tree set initialization.
    Collection<Symbol> symbols = new TreeSet<Symbol>();
    symbols.add(x);
    symbols.add(y);
    symbols.add(z);
    
    Alphabet sigma = new Alphabet(symbols);
    assertEquals("{x, y, z}", sigma.toString());
    
    sigma = new Alphabet(x);
    assertEquals("{x}", sigma.toString());
    
    sigma = new Alphabet(Symbol.EMPTY_SYMBOL);
    assertEquals("{ε}", sigma.toString());
  }
}
