package transition;

import symbol.Symbol;

import java.util.ArrayList;

/**
 * <h2>ComparableList</h2>
 *
 * Auxiliar list used to have
 * a list of symbols into a tuple.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class SymbolList extends ArrayList<Symbol> implements Comparable<SymbolList> {
  @Override
  public int compareTo(SymbolList o) {
    int sizeComp = Integer.compare(size(), o.size());
    if (sizeComp == 0) {
      for (int i = 0; i < size(); i++) {
        int comp = get(i).compareTo(o.get(i));
        if (comp != 0)
          return comp;
      }
      return 0;
    }
    return sizeComp;
  }
}
