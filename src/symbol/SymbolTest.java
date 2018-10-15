/**
 * 
 */
package symbol;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * <h2>SymbolTest</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		15/10/2018
 * @version 1.0.0
 */
public class SymbolTest {

  @Test
  public void creationTest() {
    Symbol s1 = new Symbol("a1");
    Symbol s2 = new Symbol("a2");
    assertEquals("a1", s1.toString());
    assertEquals("a2", s2.toString());
  }
  
  @Test
  public void emptySymbolCreationTest() {
    assertEquals(".", Symbol.EMPTY_SYMBOL.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void nullSymbolInitializationError() {
    new Symbol(null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void emptySymbolInitializationError() {
    new Symbol(".");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void emptyStringInitializationError() {
    new Symbol("");
  }
  
  @Test
  public void equalityTest() {
    Symbol s1 = new Symbol("a1");
    Symbol s2 = new Symbol("a2");
    Symbol s3 = new Symbol("a1");
    Symbol s4 = s3;
    
    assertNotEquals(s1, s2);
    assertEquals(s1, s3);
    assertEquals(s3, s4);
    
    assertEquals(Symbol.EMPTY_SYMBOL, Symbol.EMPTY_SYMBOL);
    assertNotEquals(Symbol.EMPTY_SYMBOL, s1);
  }
}
