package pushdown;

import static org.junit.Assert.*;

import org.javatuples.Tuple;
import org.junit.Before;
import org.junit.Test;
import pushdown.reader.PDAReader;
import state.State;
import symbol.Symbol;
import tape.Tape;
import tape.reader.TapeReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * <h2>PushdownAutomatonTest</h2>
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public class PushdownAutomatonTest {
  PDAReader reader;
  TapeReader tapeReader;

  PushdownAutomaton pda;
  Tape tape;

  @Test
  public void Automaton1Test() throws FileNotFoundException {
    String fileName = "test/AP1.txt";
    reader = new PDAReader(new FileReader(fileName));

    pda = reader.getReadedPDA();
    pda.isPrintable = true;

    String tape1 = "a a b b";
    tapeReader = new TapeReader(tape1);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertTrue(pda.evaluate(tape));
    System.out.println();

    String tape2 = "a a a b b";
    tapeReader = new TapeReader(tape2);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertFalse(pda.evaluate(tape));
    System.out.println();
  }

  @Test
  public void Automaton2Test() throws FileNotFoundException {
    String fileName = "test/AP2.txt";
    reader = new PDAReader(new FileReader(fileName));

    pda = reader.getReadedPDA();
    pda.isPrintable = true;

    String tape1 = "a a b b";
    tapeReader = new TapeReader(tape1);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertTrue(pda.evaluate(tape));
    System.out.println();

    String tape2 = "a a a b b";
    tapeReader = new TapeReader(tape2);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertFalse(pda.evaluate(tape));
    System.out.println();
  }

  @Test
  public void Automaton3Test() throws FileNotFoundException {
    String fileName = "test/AP3.txt";
    reader = new PDAReader(new FileReader(fileName));

    pda = reader.getReadedPDA();
    pda.isPrintable = true;

    String tape1 = "a a b b";
    tapeReader = new TapeReader(tape1);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertTrue(pda.evaluate(tape));
    System.out.println();

    String tape2 = "";
    tapeReader = new TapeReader(tape2);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertTrue(pda.evaluate(tape));
    System.out.println();
  }

  @Test
  public void Automaton4Test() throws FileNotFoundException {
    String fileName = "test/AP4.txt";
    reader = new PDAReader(new FileReader(fileName));

    pda = reader.getReadedPDA();
    pda.isPrintable = true;

    String tape1 = "a a b b";
    tapeReader = new TapeReader(tape1);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertTrue(pda.evaluate(tape));
    System.out.println();

    String tape2 = "";
    tapeReader = new TapeReader(tape2);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertTrue(pda.evaluate(tape));
    System.out.println();
  }

  @Test
  public void Automaton5Test() throws FileNotFoundException {
    String fileName = "test/AP5.txt";
    reader = new PDAReader(new FileReader(fileName));

    pda = reader.getReadedPDA();
    pda.isPrintable = true;

    String tape1 = "a a b b";
    tapeReader = new TapeReader(tape1);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertTrue(pda.evaluate(tape));
    System.out.println();

    String tape2 = "";
    tapeReader = new TapeReader(tape2);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertTrue(pda.evaluate(tape));
    System.out.println();
  }

  @Test
  public void Automaton6Test() throws FileNotFoundException {
    String fileName = "test/AP6.txt";
    reader = new PDAReader(new FileReader(fileName));

    pda = reader.getReadedPDA();
    pda.isPrintable = true;

    String tape1 = "1 1 1 1";
    tapeReader = new TapeReader(tape1);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertTrue(pda.evaluate(tape));
    System.out.println();

    String tape2 = "1 0 1 1";
    tapeReader = new TapeReader(tape2);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertFalse(pda.evaluate(tape));
    System.out.println();

    String tape3 = "0 1 1 0";
    tapeReader = new TapeReader(tape3);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertTrue(pda.evaluate(tape));
    System.out.println();

    String tape4 = "0 1 0";
    tapeReader = new TapeReader(tape4);
    tape = new Tape(tapeReader.getTokenizedInput(), pda.getInputAlphabet());

    assertFalse(pda.evaluate(tape));
    System.out.println();
  }
}
