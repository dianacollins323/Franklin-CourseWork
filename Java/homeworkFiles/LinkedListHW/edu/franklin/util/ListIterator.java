package edu.franklin.util;


/**
 * An object that abstracts the idea of iterator (both forward and
 * backward) over a list of objects.  It also allows the replacement
 * of elements on the fly, and the extraction of the iterator position
 * in terms of an index value between elements (<tt>0</tt> being
 * <em>before</em>the 0th element, and <tt>n+1</tt> being <em>after</em>
 * the nth element).
 * @param <E> the type of data returned
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */

public interface ListIterator<E> extends Iterator<E>
{
    /**
     * Inserts the specified element into the list at the present iterator
     * position.  The insertion point is before the element that would be
     * returned by a call to <tt>next</tt> and after the element that would
     * be returned by a call to <tt>previous</tt>.  After adding the element,
     * a call to <tt>previous</tt> will return the newly inserted element,
     * but a call to <tt>next</tt> will return the element immediately
     * after the one inserted.
     * @param obj the element to insert
     */
    void add(E obj);
    
    /**
     * Returns true when the iterator has more data to return in the
     * reverse direction.
     * @return true if the iterator has more data
     */
    boolean hasPrevious();
    
    /**
     * Returns the index of the element that would be returned should
     * a subsequent call to <tt>next</tt> be made.
     * @return the index of the next element
     */
    int nextIndex();
    
    /**
     * Returns the element from the iteration when traversing in the
     * reverse direction.
     * @return the previous data element
     */
    E previous();
    
    /**
     * Returns the index of the element that would be returned should
     * a subsequent call to <tt>previous</tt> be made.
     * @return the index of the previous element
     */
    int previousIndex();
    
    /**
     * Replaces the last element returned by either <tt>next</tt> or
     * <tt>previous</tt>.  Cannot be called immediately after <tt>add</tt>
     * or <tt>remove</tt>.
     * @param obj the object with which to replace the one returned
     * earlier.
     */
    void set(E obj);
}
