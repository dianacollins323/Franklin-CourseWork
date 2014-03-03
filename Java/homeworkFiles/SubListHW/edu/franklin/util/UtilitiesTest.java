package edu.franklin.util;

/**
 * Tests for static utility functions used in our collections hierarchy.
 *
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class UtilitiesTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class UtilitiesTest.
     */
    public UtilitiesTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
    
    /**
     * General purpose test for resizing arrays.
     * @param <E> Type of data in the array
     * @param orig Array of data from which to copy
     * @param newSize the size of the new array to make
     */
    private <E> void resizeArrayHelper(E [] orig, int newSize)
    {
        E [] result = Utilities.resizeArray(orig, newSize);
        assertEquals(newSize, result.length);
        
        int i;
        for (i = 0; orig != null && i < orig.length && i < result.length; ++i)
        {
            assertEquals(orig[i], result[i]);
        }
        for ( ; i < result.length; ++i)
        {
            assertNull(result[i]);
        }
    }
    
    /**
     * Test growing an array.
     */
    public void testResizeArrayGrow()
    {
        resizeArrayHelper(
            new Integer [] {1, 2, 3, 4, 5, 6, 7, 8},
            10);
    }
    
    /**
     * Test growing from null array.
     */
    public void testResizeArrayNull()
    {
        Integer [] arr = null;
        resizeArrayHelper(arr, 10);
    }
    
    /**
     * Test shrinking an array.
     */
    public void testResizeArrayShrink()
    {
        resizeArrayHelper(
            new Integer [] {1, 2, 3, 4, 5, 6, 7, 8},
            4);
    }

    /**
     * Test that nullSafeEquals works.
     */
    public void testNullSafeEquals()
    {
        assertTrue(Utilities.nullSafeEquals(null, null));
        assertFalse(Utilities.nullSafeEquals(null, "testme"));
        assertFalse(Utilities.nullSafeEquals("testme", null));
        assertTrue(Utilities.nullSafeEquals("testme", "testme"));
    }
}
