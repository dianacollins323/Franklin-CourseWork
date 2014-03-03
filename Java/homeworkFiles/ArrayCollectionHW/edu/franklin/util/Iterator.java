package edu.franklin.util;

/**
 * An object that abstracts the idea of iterating (looping) over
 * a collection of objects.
 * @param <E> the type of data returned
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */

public interface Iterator<E> extends java.util.Iterator<E>
{
    /**
     * Returns true when the iterator has more data to return.
     * @return true if the iterator has more data
     */
    boolean hasNext();
    
    /**
     * Returns the next element from the iteration.
     * @return the next data element
     */
    E next();

    /**
     * Removes the previously returned element from the iteration.
     * @throws IllegalStateException when next is not called
     * immediately before remove.
     */
    void remove();
}
