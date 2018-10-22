package pushdown.reader;

import static org.junit.Assert.*;

import org.javatuples.Tuple;
import org.junit.Before;
import org.junit.Test;
import pushdown.PushdownAutomaton;
import state.State;
import symbol.Symbol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * <h2>PDAReaderTest</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class PDAReaderTest {

  PushdownAutomaton pda;
  PDAReader reader;
  FileReader apf1File;
  FileReader apf2File;

  @Before
  public void setUp() throws FileNotFoundException {
    apf1File = new FileReader("test/APf.txt");
    apf2File = new FileReader("test/APf-2.txt");
  }

  @Test
  public void creationTest() {
    reader = new PDAReader(apf1File);
    pda = reader.getReadedPDA();
    System.out.println(pda);
  }
}
