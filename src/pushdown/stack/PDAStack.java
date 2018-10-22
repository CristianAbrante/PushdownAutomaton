package pushdown.stack;

import symbol.Symbol;

import java.util.Stack;

/**
 * <h2>PDAStack</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class PDAStack extends Stack<Symbol> {
  public Symbol push(Symbol symbol) {
    if (symbol == null)
      throw new NullPointerException("symbol can not be null.");

    return super.push(symbol);
  }

  public Symbol pop() {
    return super.pop();
  }
}
