import java.text.NumberFormat;

/**
 * An implementation of the Money interface for the US Dollar.  Objects of
 * this class are immutable.
 * 
 * @author Todd A. Whittaker
 * @version 2006-01
 */
public class Dollar implements Money
{
    /** internal representation as a long number of pennies. */
    private long value;
    
    /**
     * Build a zero-valued dollar.
     */
    public Dollar()
    {
        this(0);
    }
    
    /**
     * Build a dollar from the given dollars and cents.  If cents is
     * more than 100, then the number of dollars is increased.  i.e.
     * <tt>new Dollar(5, 225)</tt> is equivalent to
     * <tt>new Dollar(7, 25)</tt>.
     * @param dollars the dollar count
     * @param cents the cents count
     */
    public Dollar(int dollars, int cents)
    {
        this(dollars * 100 + cents);
    }
    
    /**
     * Build a dollar from the given number of cents.  The number
     * of dollars is cents/100.
     * @param cents the cents count
     */
    public Dollar(long cents)
    {
        this.value = cents;
    }
    
    /**
     * Build a dollar from the given amount.  The amount is considered
     * to be a number of dollars with the fractional part as cents.  For
     * example, <tt>new Dollar(7.25)</tt> is equivalent to
     * <tt>new Dollar(7, 25)</tt>.
     */
    public Dollar(double amount)
    {
        this((long)Math.round(amount * 100.0));
    }
    
    /**
     * Build a dollar as a duplicate of the specified dollar.
     * @param other the dollar to duplicate.
     */
    public Dollar(Dollar other)
    {
        this(other.value);
    }
    
    /**
     * Convert the current money object into a double value.
     * @return the double value of <tt>this</tt> money object.
     */
    public double asDouble()
    {
        return this.value / 100.0;
    }
    
    /**
     * Convert the current money object into a long.  Since there is no
     * decimal place in a long integer, the actual result is the same
     * as Math.floor(Money.asDouble() * 100);
     */
    public long asLong()
    {
        return this.value;
    }
    
    /**
     * Determines if the money object represents a negative quantity.
     * @return true if the money object is negative, false otherwise.
     */
    public boolean isNegative()
    {
        return this.value < 0;
    }
    
    /**
     * Produce the absolute value of <tt>this</tt> money object.
     * @return the absolute value
     */
    public Money abs()
    {
        return this.isNegative() ? this.neg() : this;
    }
    
    /**
     * Negates the current money object.
     * @return the negative of the current money object.
     */
    public Money neg()
    {
        return new Dollar(-this.value);
    }
    

    /**
     * Divide <tt>this</tt> mony object by the <tt>other</tt> money
     * object to produce a dimensionless ratio.For example:
     * <pre>
     *    Money first, second;
     *    // ...
     *    Money result = first.div(second);
     * </pre>
     * is similar to:
     * <pre>
     *    double first, second;
     *    // ...
     *    double result = first / second;
     * </pre>
     * @param other the money object to divide into <tt>this</tt> one.
     * @return the ratio of <tt>this</tt> and <tt>other</tt>
     */
    public double div(Money other)
    {
        return this.asDouble() / other.asDouble();
    }
    
    /**
     * Divide <tt>this</tt> money object by a fixed amount to
     * produce a scaled money object.
     * <pre>
     *    Money money;
     *    double count;
     *    // ...
     *    Money result = money.div(count);
     * </pre>
     * is similar to:
     * <pre>
     *    double money, count;
     *    // ...
     *    double result = money / count;
     * </pre>
     * @param amount the amount to multiply by
     * @return the product of <tt>this</tt> and <tt>amt</tt>
     */
    public Money div(double amount)
    {
        return new Dollar(this.asDouble() / amount);
    }
    
    /**
     * Multiply <tt>this</tt> money object by a fixed amount to
     * produce a scaled money object.  It doesn't make sense to 
     * multiply two Money objects together, but it does make sense
     * to multiply by a percentage, for example.
     * <pre>
     *    Money money;
     *    double percent;
     *    // ...
     *    Money result = money.mul(percent);
     * </pre>
     * is similar to:
     * <pre>
     *    double money, percent;
     *    // ...
     *    double result = money * percent;
     * </pre>
     * @param amount the amount to multiply by
     * @return the product of <tt>this</tt> and <tt>amt</tt>
     */
    public Money mul(double amount)
    {
        return new Dollar(this.asDouble() * amount);
    }
    
    /**
     * Subtract the <tt>other</tt> money object from <tt>this</tt> money
     * object to produce a money object difference.  For example:
     * <pre>
     *    Money first, second;
     *    // ...
     *    Money result = first.sub(second);
     * </pre>
     * is similar to:
     * <pre>
     *    double first, second;
     *    // ...
     *    double result = first - second;
     * </pre>
     * @param other the money object to be subtracted from <tt>this</tt> one.
     * @return the difference of <tt>this</tt> and <tt>other</tt>
     */
    public Money sub(Money other)
    {
        return this.add(other.neg());
    }
    
    /**
     * Add the <tt>other</tt> money object to <tt>this</tt> money
     * object to produce a money object sum.  For example:
     * <pre>
     *    Money first, second;
     *    // ...
     *    Money result = first.add(second);
     * </pre>
     * is similar to:
     * <pre>
     *    double first, second;
     *    // ...
     *    double result = first + second;
     * </pre>
     * @param other the money object to be added to <tt>this</tt> one.
     * @return the sum of <tt>this</tt> and <tt>other</tt>
     */
    public Money add(Money other)
    {
        return new Dollar(this.asLong() + other.asLong()); 
    }

    /**
     * Overrides <tt>Object.equals()</tt> to compare as Money.
     * @param o the object against which to compare.
     * @return true if the two objects are equal by value
     */
    public boolean equals(Object o)
    {
        if (!(o instanceof Money))
        {
            return false;
        }
        return ((Money)o).asLong() == this.asLong();
    }
    
    /**
     * Compare two money objects.  Yields a positive number if
     * <tt>this</tt> is greater than <tt>other</tt>, a negative
     * number if <tt>this</tt> is less than <tt>other</tt> and zero
     * if they are equal.
     * @return -1, 0, or 1 according to the above.
     */
    public int compareTo(Money other)
    {
        if (this.asLong() > other.asLong())
        {
            return 1;
        }
        else if (this.asLong() < other.asLong())
        {
            return -1;
        }
        return 0;
    }
    
    /**
     * The string representation in USD.
     * @return a string representing the money.
     */
    public String toString()
    {
        return NumberFormat.getCurrencyInstance().format(this.asDouble());
    }
}
