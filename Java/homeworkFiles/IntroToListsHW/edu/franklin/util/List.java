package edu.franklin.util;

/**
 * List is an extension of collection that adds the concepts of order,
 * indexed operations and bi-directional iteration.  The order of the
 * collection is guaranteed (unlike sets or collections).  Operations
 * may now take place at particular indices.  Finally, the list iterator
 * can move both forward and backward through the list and add elements
 * as well as remove them at iterator positions.
 * @param <E> the type of data to hold in the list
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */

public interface List<E> extends Collection<E>
{
   /**
     * Inserts the element into the list at the given location.
     * @param index the location at which to insert
     * @param obj the object to add to the collection
     */
    void add(int index, E obj);

    /**
     * Inserts all the elements of <tt>coll</tt> into this collection at
     * the specified location.  If both this collection and the parameter
     * are the same collection, then the operational behavior is undefined
     * (i.e. bad things can happen).
     * @param index the location at which to insert
     * @param coll the collection from which to draw elements for addition.
     * @return true when this collection is modified as a result.
     */
    boolean addAll(int index, Collection<? extends E> coll);
    
    /**
     * Returns the element of the list located at the specified index.
     * @param index the location of the element
     * @return the element at the index
     */
    E get(int index);
    
    /**
     * Returns the location in the list of the first element that matches
     * the parameter object <tt>obj</tt> according to its equals method.
     * @param obj the object for which to search
     * @return the first index (from 0) where the object is found, or -1
     * if the object is not found in the list
     */
    int indexOf(Object obj);
    
    /**
     * Returns the location in the list of the last element that matches
     * the parameter object <tt>obj</tt> according to its equals method.
     * @param obj the object for which to search
     * @return the last index (from 0) where the object is found, or -1
     * if the object is not found in the list
     */
    int lastIndexOf(Object obj);
    
    /**
     * Returns a ListIterator over the elements in this list.  The starting
     * position for the list iterator will be the 0th element.
     * @return a ListIterator over the elements in this list.
     */
    ListIterator<E> listIterator();
    
    /**
     * Returns a ListIterator over the elements in this list starting at
     * the given position.
     * @param index the starting index for iteration
     * @throws IndexOutOfBoundsException if the index < 0 or index > size()
     */
    ListIterator<E> listIterator(int index);
    
    /**
     * Removes the element from the list at the specified index.
     * @param index the index of the object to be removed
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    E remove(int index);
    
    /**
     * Replaces the element at the specified index with the parameter
     * element.  Returns the element which was replaced.
     * @param index the index of the object to be replaced
     * @param obj the object to use as a replacement
     * @return the object originally at the location
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    E set(int index, E obj);
    
    /**
     * Create a sublist of this list between the given indices.  The sublist
     * is not a copy of the elements of this list, but rather a restricted
     * view on them.  Thus, any insertions, deletions, or replacements
     * of elements in the sublist will affect the original list.
     * @param fromIndex the starting index (inclusive) of the sublist
     * @param toIndex the ending index (exclusive) of the sublist
     * @return a restricted view on this list
     * @throws IndexOutOfBoundsException if either index is < 0 or > size.
     */
    List<E> subList(int fromIndex, int toIndex);
}
