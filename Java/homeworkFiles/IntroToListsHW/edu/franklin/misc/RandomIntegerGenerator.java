package edu.franklin.misc;
import java.util.Random;

/**
 * A generator of random Integer data.
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class RandomIntegerGenerator implements DataGenerator<Integer>
{
    private Random random;
    private long seed;
    private int totalCount;
    private int currentCount;
    
    // the seedUniquifier is lifted from Sun's implementation of Random
    private static volatile long seedUniquifier = 8682522807148012L;

    /**
     * Construct a generator based on the system time.
     * @param count the number of random integers to generate
     */
    public RandomIntegerGenerator(int count)
    {
        this (seedUniquifier + System.nanoTime(), count);
    }
    
    /**
     * Construct a generator based on the provided seed.
     * @param initialSeed the seed for a linear congruential
     * random number generator
     * @param count the number of random integers to generate
     */
    public RandomIntegerGenerator(long initialSeed, int count)
    {
        this.seed = initialSeed;
        this.totalCount = count;
        this.currentCount = 0;
        random = new Random(this.seed);
    }
    
    /**
     * Returns true when the generator has more data to return.
     * @return true if the generator has more data
     */
    public boolean hasNext()
    {
        return this.currentCount < this.totalCount;
    }
    
    /**
     * Returns the next element from the generator.
     * @return the next data element
     */
    public Integer next()
    {
        ++this.currentCount;
        return new Integer(random.nextInt());
    }
    
    /**
     * Resets the generator back to the initial state.
     * @return the reset generator.
     */
    public DataGenerator<Integer> reset()
    {
        this.random.setSeed(this.seed);
        this.currentCount = 0;
        return this;
    }
}
