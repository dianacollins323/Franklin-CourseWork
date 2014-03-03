package edu.franklin.util;

/**
 * An interface that allows the use of the "foreach" statement.
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 * @param <E> type iterated over
 */

public interface Iterable<E> extends java.lang.Iterable<E>
{
    /**
     * Generate an iterator object.
     * @return an iterator object.
     */
    Iterator<E> iterator();
}
