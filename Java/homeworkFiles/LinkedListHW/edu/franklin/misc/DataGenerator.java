package edu.franklin.misc;

/**
 * Produce some test data that can be put in collections.
 * @param <E> the type of data generated
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */

public interface DataGenerator<E>
{
    /**
     * Returns true when the generator has more data to return.
     * @return true if the generator has more data
     */
    boolean hasNext();
    
    /**
     * Returns the next element from the generator.
     * @return the next data element
     */
    E next();
    
    /**
     * Resets the generator back to the initial state.
     * @return the reset generator.
     */
    DataGenerator<E> reset();
}
