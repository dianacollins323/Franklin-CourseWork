package edu.franklin.util;
import java.lang.reflect.Array;

/**
 * A set of utility functions needed in collections.
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class Utilities
{
    /**
     * Grows or shrinks an array.  Depending on parameters, if
     * newSize is greater than the original array length, then
     * the array grows to newSize.  If it is less, then it shrinks.
     * Elements are copied from the original to the new array, leaving
     * the original unchanged. orig can be <tt>null</tt>, in which
     * case nothing is copied.
     * @param <E> The type of elements
     * @param orig the original array
     * @param newSize the size of the new array
     * @return a new array with data copied.
     */
    public static <E> E[] resizeArray(E[] orig, int newSize)
    {
        int oldSize = orig == null ? 0 : orig.length;

        // the following line will generate a compiler warning
        E[] data = newArray(orig, newSize); //(E[])new Object[newSize];
        
        int i = 0;
        for (i = 0; i < oldSize && i < newSize; ++i)
        {
            data[i] = orig[i];
        }
        return data;
    }
    
    /**
     * Creates a new array of the specified size based on the type
     * of the array given as a parameter.  No data is copied.
     * @param <E> The type of elements
     * @param orig the original array
     * @param size the size of the new array
     * @return a new array of the type and size specified.
     */
    public static <E> E[] newArray(E[] orig, int size)
    {
        if (orig == null)
        {
            return (E[])(new Object[size]);
        }
        // the following line will generate a compiler warning
        return (E[])Array.newInstance(
            orig.getClass().getComponentType(),
            size);
    }
    
    /**
     * Compares two objects for equality in a way that should never
     * throw a NullPointerException.
     * @param obj1 the first object for comparison
     * @param obj2 the second object for comparison
     * @return true of obj1 and obj2 are equal (even if null)
     */
    public static boolean nullSafeEquals(Object obj1, Object obj2)
    {
        return obj1 == null ? obj2 == null : obj1.equals(obj2);
    }
}
