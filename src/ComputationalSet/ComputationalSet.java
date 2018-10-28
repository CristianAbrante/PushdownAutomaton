package ComputationalSet;

import java.util.*;

/**
 * <h2>ComputationalSet</h2>
 *
 * This set models most of the
 * behaviours used by sets of
 * computational machines.
 *
 * It implements some helpful
 * methods used by other
 * subsets.
 *
 * @author Cristian Abrante
 * @version 1.0.0
 */
public abstract class ComputationalSet<E>
        extends AbstractSet<E> {
  /**
   * For complexity reasosns an
   * internal set is used.
   */
  private Set<E> internalSet;

  /**
   * Constructor of the class.
   * It creates an empty set.
   */
  public ComputationalSet() {
    setInternalSet(new TreeSet<>());
  }

  /**
   * Constructor of the class.
   *
   * It creates a set with only
   * one element
   *
   * @param e element that set
   *          is going to contain.
   * @throws NullPointerException
   *          if element is {@code null}.
   *
   */
  public ComputationalSet(E e) {
    this();
    add(e);
  }

  /**
   * Constructor of the class.
   *
   * It creates a set with all
   * elements contained in the
   * collection.
   *
   * @param c collection to add.
   */
  public ComputationalSet(Collection<? extends E> c) {
    this();
    addAll(c);
  }

  /**
   * Tests if set not contains
   * any element.
   *
   * @return {@code true} if set is empty.
   */
  @Override
  public boolean isEmpty() {
    return getInternalSet().isEmpty();
  }

  /**
   * Test if set contains specified
   * object.
   *
   * @param o object we are testing
   *          if it is in the set.
   * @return {@code true} if object is
   *          in the set.
   * @throws NullPointerException
   *          if {@code null} is {@code null}.
   */
  @Override
  public boolean contains(Object o) {
    return getInternalSet().contains(o);
  }

  /**
   * Converts this set into an
   * array of objects.
   *
   * @return the array of objects.
   */
  @Override
  public Object[] toArray() {
    return getInternalSet().toArray();
  }

  /**
   * Adds the specified element
   * to the array.
   *
   * @param e element to be added.
   * @return {@code true} if set didn't
   *          contained specified element.
   * @throws NullPointerException if
   *          element to be added is {@code null}.
   */
  @Override
  public boolean add(E e) {
    return getInternalSet().add(e);
  }

  /**
   * Removes the specified element
   * from the set.
   *
   * @param o the element we want to
   *          remove.
   * @return {@code true} if the set
   *          contained the specified
   *          element.
   * @throws NullPointerException if element
   *          is {@code null}.
   */
  @Override
  public boolean remove(Object o) {
    return getInternalSet().remove(o);
  }

  /**
   * Test if st contains all elements
   * on the collection.
   *
   * @param c collection of elements
   * @return {@code true} if the set
   *          contained all elements
   *          on the collection.
   * @throws NullPointerException if
   *          collection is null or any
   *          element is null.
   */
  @Override
  public boolean containsAll(Collection<?> c) {
    return getInternalSet().containsAll(c);
  }

  /**
   * Adds elements on the collection
   * to the set.
   *
   * @param c collection of elements
   *          we want to add.
   * @return {@code true} if this set
   *          changed as a result of
   *          the call.
   * @throws NullPointerException if
   *          collection or any element
   *          inside the collection is
   *          {@code null}.
   */
  @Override
  public boolean addAll(Collection<? extends E> c) {
    return getInternalSet().addAll(c);
  }

  /**
   * Removes all elements on the
   * set.
   */
  @Override
  public void clear() {
    getInternalSet().clear();
  }

  /**
   * Returns a string that
   * represents the set, in this
   * format:
   *
   * {e1, e2, ..., en}
   *
   * @return a {@code String} containing
   *          the elements
   */
  @Override
  public String toString() {
    String computationalSet = "{";
    int index = 0;
    for (E elem : this) {
      computationalSet += elem;
      if (index < size() - 1)
        computationalSet += ", ";
      index += 1;
    }
    computationalSet += "}";
    return computationalSet;
  }

  /**
   * Return an interator
   * of the set.
   *
   * @return an iterator of the
   *          set.
   */
  @Override
  public Iterator<E> iterator() {
    return getInternalSet().iterator();
  }

  /**
   * Returns the size of the
   * set.
   *
   * @return size of the set.
   */
  @Override
  public int size() {
    return getInternalSet().size();
  }

  /**
   * Test if two computational sets
   * are equal.
   *
   * @param o the other set to compare.
   * @return {@code true} if two sets are
   *          equals.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ComputationalSet)) return false;
    if (!super.equals(o)) return false;
    ComputationalSet<?> that = (ComputationalSet<?>) o;
    return Objects.equals(internalSet, that.internalSet);
  }

  /**
   * Return the hash code of the
   * object.
   *
   * @return the hash code.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), internalSet);
  }

  /**
   * Method that returns the
   * equal element on the set
   * if present.
   *
   * @param e element to be checked.
   * @return the element if it is present
   *          and {@code null} otherwise.
   * @throws NullPointerException if element
   *          is {@code null}.
   */
  public E get(E e) {
    if (e == null)
      throw new NullPointerException("element can not be null.");

    for (E elem : this) {
      if (elem.equals(e)) {
        return elem;
      }
    }
    return null;
  }

  /**
   * Returns the internal set of
   * the set.
   *
   * @return internal set.
   */
  private Set<E> getInternalSet() {
    return internalSet;
  }

  /**
   * Sets the internal set.
   *
   * @param internalSet we want to set.
   * @throws NullPointerException if
   *          internal set is {@code null}.
   */
  private void setInternalSet(Set<E> internalSet) {
    if (internalSet == null)
      throw new NullPointerException("internal set can not be null.");

    this.internalSet = internalSet;
  }
}
