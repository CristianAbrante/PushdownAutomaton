package transition;

import static org.junit.Assert.*;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Tuple;
import org.junit.Before;
import org.junit.Test;
import state.State;
import symbol.Symbol;

public class TransitionTest {

  Tuple currentState;
  Tuple nextState;

  @Before
  public void creationOfTuples() {
    currentState = new Triplet<State, Symbol, Symbol>(new State("q0"),
      new Symbol("a"),
      new Symbol("A"));
    nextState = new Pair<State, Symbol>(new State("q1"),
      new Symbol("B"));
  }

  @Test
  public void creationTest() {
    Transition transition = new Transition(currentState, nextState);
  }

  @Test(expected = NullPointerException.class)
  public void currentStateNullCreationTest() {
    new Transition(null, nextState);
  }

  @Test(expected = NullPointerException.class)
  public void nextStateNullCreationTest() {
    new Transition(currentState, null);
  }

  @Test
  public void equalsTest() {
    Transition transition1 = new Transition(currentState, nextState);
    Transition transition2 = new Transition(currentState, nextState);
    Transition transition3 = new Transition(nextState, currentState);

    assertEquals(transition1, transition2);
    assertNotEquals(transition1, transition3);
  }
}
