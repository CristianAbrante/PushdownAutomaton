package pushdown.stack;

import symbol.Symbol;
import transition.SymbolList;

import java.util.Stack;

/**
 * <h2>PDAStack</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class PDAStack extends Stack<Symbol> {

  public PDAStack() {
    super();
  }

  public PDAStack(PDAStack that) {
    this();
    for (Symbol symbol : that) {
      add(new Symbol(symbol.toString()));
    }
  }

  public Symbol push(Symbol symbol) {
    if (symbol == null)
      throw new NullPointerException("symbol can not be null.");

    return symbol.equals(Symbol.EMPTY_SYMBOL) ?
            Symbol.EMPTY_SYMBOL :
            super.push(symbol);
  }

  public void push(SymbolList symbols) {
    for (int i = symbols.size() - 1; i >= 0; --i) {
      push(symbols.get(i));
    }
  }

  public Symbol pop() {
    return super.pop();
  }

  public PDAStack clone() {
    PDAStack clonedStack = new PDAStack();
    for (Symbol symbol : this) {
      clonedStack.push(symbol);
    }
    return clonedStack;
  }
}
