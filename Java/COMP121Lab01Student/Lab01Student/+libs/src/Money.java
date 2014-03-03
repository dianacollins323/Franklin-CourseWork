/**
 * An interface that abstracts operations on Money.  All money objects
 * should be immutable (i.e. none of the methods make changes to
 * the implicit parameter).
 * 
 * @author Todd A. Whittaker
 * @version 2006-01
 */

public interface Money
{
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
    Money add(Money other);
    
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
    Money sub(Money other);
    
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
     * @param amt the amount to multiply by
     * @return the product of <tt>this</tt> and <tt>amt</tt>
     */
    Money mul(double amt);

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
     * @param amt the amount to multiply by
     * @return the product of <tt>this</tt> and <tt>amt</tt>
     */
    Money div(double amt);

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
    double div(Money other);
    
    /**
     * Negates the current money object.
     * @return the negative of the current money object.
     */
    Money neg();
    
    /**
     * Determines if the money object represents a negative quantity.
     * @return true if the money object is negative, false otherwise.
     */
    boolean isNegative();
    
    /**
     * Produce the absolute value of <tt>this</tt> money object.
     * @return the absolute value
     */
    Money abs();
    
    /**
     * Convert the current money object into a double value.
     * @return the double value of <tt>this</tt> money object.
     */
    double asDouble();
    
    /**
     * Convert the current money object into a long.  Since there is no
     * decimal place in a long integer, the actual result is the same
     * as Math.floor(Money.asDouble() * 100);
     */
    long asLong();
    
    /**
     * Compare two money objects.  Yields a positive number if
     * <tt>this</tt> is greater than <tt>other</tt>, a negative
     * number if <tt>this</tt> is less than <tt>other</tt> and zero
     * if they are equal.
     * @return -1, 0, or 1 according to the above.
     */
    int compareTo(Money other);
}
