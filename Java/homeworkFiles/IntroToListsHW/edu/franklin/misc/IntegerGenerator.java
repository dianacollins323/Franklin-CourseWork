package edu.franklin.misc;

/**
 * Generates a sequence of Integer objects starting from 0.
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class IntegerGenerator implements DataGenerator<Integer>
{
    private int startValue;
    private int currentCount;
    private int totalCount;
    private int nextValue;
    
    /**
     * Construct a generator that will return <tt>count</tt> Integers
     * starting at <tt>start</tt>.
     * @param start the starting number for the sequence
     * @param count the count of elements to generate
     */
    public IntegerGenerator(int start, int count)
    {
        this.startValue = start;
        this.totalCount = count;
        this.nextValue = start;
        this.currentCount = 0;
    }
    
    /**
     * Construct a generator that will return <tt>count</tt> elements
     * starting at zero (0).
     * @param count the count of elements to generate.
     */
    public IntegerGenerator(int count)
    {
        this(0, count);
    }
    
    /**
     * Returns true when the generator has more data to return.
     * @return true if the generator has more data
     */
    public boolean hasNext()
    {
        return currentCount < totalCount;
    }
    
    /**
     * Returns the next element from the generator.
     * @return the next data element
     */
    public Integer next()
    {
        ++currentCount;
        return new Integer(this.nextValue++);
    }
    
    /**
     * Resets the generator back to the initial state.
     * @return the reset generator.
     */
    public IntegerGenerator reset()
    {
        this.nextValue = this.startValue;
        this.currentCount = 0;
        return this;
    }
}
