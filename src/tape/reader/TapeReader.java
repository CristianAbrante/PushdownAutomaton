package tape.reader;

import reader.AbstractReader;
import symbol.Symbol;
import tape.Tape;
import transition.Transition;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>TapeReader</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class TapeReader
        extends AbstractReader {
  List<Tape> readTapes;

  public TapeReader(FileReader file) {
    super(file);
  }

  public TapeReader(String definition) {
    super(definition);
  }

  public List<Tape> getReadTapes() {
    return readTapes;
  }

  @Override
  public void setDefinition(String definition) {
    super.setDefinition(definition);
    readTapes = new ArrayList<>();
    if (definition.isEmpty()) {
      readTapes.add(new Tape());
    }
    while (hasMoreLines()) {
      String tapeDefinition = getNextLine();
      List<Symbol> symbols = super.readSymbols(tapeDefinition);
      getReadTapes().add(new Tape(symbols));
    }
  }

  @Override
  protected Transition readTransition(String transitionString) {
    return null;
  }
}
