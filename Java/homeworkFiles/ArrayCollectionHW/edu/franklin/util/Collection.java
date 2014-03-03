package edu.franklin.util;

/**
 * The base interface for the collections hierarchy.  A collection is a group
 * of objects kept in a container.  Typical operations are to add, remove,
 * and iterate over the elements.
 * @param <E> the type of data to hold in the collection
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */

public interface Collection<E> extends Iterable<E>
{
    /**
     * Inserts an element into a collection.  Returns true if the collection
     * is changed as a result of the operation, and false otherwise.
     * @param obj the object to add to the collection
     * @return true if the collection is altered
     */
    boolean add(E obj);

    /**
     * Returns the number of elements in the collection.
     * @return the number of elements in the collection.
     */
    int size();

    /**
     * Returns true if the collection contains no elements.
     * @return true if the collection is empty.
     */
    boolean isEmpty();

    /**
     * Removes all elements from the collection.  The collection will
     * be empty after calling this method.
     */
    void clear();

    /**
     * Searches the collection to determine if the collection contains
     * an element that matches the specified object.  If the specified
     * object is null, it will look for a null in the collection.  Otherwise
     * it will use the <tt>equals</tt> method of the given object to 
     * determine equality.  That is, <tt>o == null ? element == null : 
     * o.equals(element)</tt>.
     * @param obj the object to match
     * @return true if the collection contains a matching element
     */
    boolean contains(Object obj);

    /**
     * Searches this collection to determine if every element of <tt>coll</tt>
     * exists in this collection.  Comparison is based on the same algorithm
     * as for <tt>contains</tt>.
     * @param coll the collection to be checked for containment in this on
     * @return true if all elements of <tt>coll</tt> also exist in this.
     */
    boolean containsAll(Collection<? extends E> coll);

    /**
     * Inserts all the elements of <tt>coll</tt> into this collection.  If
     * both this collection and the parameter are the same collection, then
     * the operational behavior is undefined (i.e. bad things can happen).
     * @param coll the collection from which to draw elements for addition.
     * @return true when this collection is modified as a result.
     */
    boolean addAll(Collection<? extends E> coll);

    /**
     * Removes the first element of this collection matching the parameter
     * object <tt>obj</tt>.  If the colleciton is altered as a result of
     * the operation, <tt>remove</tt> returns <tt>true</tt>.
     * @param obj the object to match
     * @return true if the collection is altered, false otherwise.
     */
    boolean remove(Object obj);

    /**
     * Removes all elements in this collection that match those in the
     * parameter collection <tt>coll</tt>.  When the operation completes,
     * the two collections will be disjoint.
     * @param coll the collection of elements to be removed from this one.
     * @return true if this collection is altered as a result of the call.
     */
    boolean removeAll(Collection<?> coll);

    /**
     * Retains all the elements in this collection that match those in
     * the parameter collection <tt>coll</tt>.  That is, it will remove
     * all elements in this collection that have no match in <tt>coll</tt>.
     * @parma coll the collection of elements to be matched against
     * @return true if this collection is altered as a result of the call.
     */
    boolean retainAll(Collection<?> coll);

    /**
     * Compares the parameter object <tt>obj</tt> against this collection
     * for equality.  Care should be taken to ensure symmetry, transitivity,
     * and reflexivity properties of equality.  That is: if
     * <tt>a.equals(b)</tt> is true then <tt>b.equals(a)</tt> should also be
     * true (symmetry); if <tt>a.equals(b)</tt> and <tt>b.equals(c)</tt> then
     * <tt>a.equals(c)</tt> should be true (transitivity); and also
     * <tt>a.equals(a)</tt> should also be true.
     * 
     * The contract between <tt>equals</tt> and <tt>hashCode</tt> should
     * also be kept.  That is, if <tt>a.equals(b)</tt> is true then
     * <tt>a.hashCode() == b.hashCode()</tt> should also be true.  The
     * reverse is not true (equal hash codes do not imply object equality).
     * 
     * A typical way to implement <tt>equals</tt> would be do do a value
     * comparison of the contents of the collection.
     * 
     * @param obj the object against which to compare this collection.
     * @return true if the object is equal to this collection.
     */
    boolean equals(Object obj);

    /**
     * Returns the hash code value for this collection.  A typical
     * implementation would be to somehow aggregate the hash codes of the
     * individual elements of the collection.  Be sure that hashCode is
     * also overridden if equals is overridden.
     * @return the hash code of the collection.
     */
    int hashCode();

    /**
     * Creates and returns an array containing the data from this collection.
     * If this collection preserves order, then the order in the array
     * will be the same as the order of the collection.  The array returned
     * will always be safe to be modified (i.e. it will be different from
     * the underlying collection's array if the collection is array-backed).
     * @return an array containing references to the collections elements.
     */
    Object [] toArray();

    /**
     * Returns an array containing the data from this collection.  If the
     * parameter array is large enough to hold the collection, then it is
     * filled and returned.  Otherwise, a new array of the same type is
     * allocated, filled, and returned.  If this collection preserves order
     * then ten the order in the array will be the same as the order of the
     * collection. The array returned will always be safe to be modified
     * (i.e. it will be different from the underlying collection's array
     * if the collection is array-backed).
     * @return an array containing references to the collections elements.
     */
    <E> E[] toArray(E[] a);
}
