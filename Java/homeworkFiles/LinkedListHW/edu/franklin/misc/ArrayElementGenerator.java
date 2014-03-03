package edu.franklin.misc;


/**
 * A generator that hands back elements of an array.  This is a thin
 * wrapper around the data.  No deep copy is made of the array.
 * @param <E> the type of data being returned by the generator
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class ArrayElementGenerator<E> implements DataGenerator<E>
{
    private E [] data;
    private int cursor;
    
    /**
     * Construct a generator from the specified array.
     * @param arr the array containing data to return.
     */
    public ArrayElementGenerator(E [] arr)
    {
        this.data = arr;
    }
    
    /**
     * Returns true when the generator has more data to return.
     * @return true if the generator has more data
     */
    public boolean hasNext()
    {
        return this.data != null && this.cursor < this.data.length;
    }
    
    /**
     * Returns the next element from the generator.
     * @return the next data element
     */
    public E next()
    {
        return this.data[this.cursor++];
    }
    
    /**
     * Resets the generator back to the initial state.
     * @return the reset generator.
     */
    public DataGenerator<E> reset()
    {
        this.cursor = 0;
        return this;
    }
}
