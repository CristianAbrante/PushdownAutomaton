package tape;

public enum Movement {
  LEFT(-1),
  STOP(0),
  RIGHT(1);

  public int value;
  Movement(int value) {
    this.value = value;
  }
}