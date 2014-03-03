package edu.franklin.util;
import java.util.Random;
import java.util.Calendar;

/**
 * The test class SubListTest.  Determines if the decorated list operations
 * actually affect the original list.
 *
 * @author  Todd A. Whittaker
 * @version 2005-09
 */
public class SubListTest extends junit.framework.TestCase
{
    private static final int SIZE = 100;
    private List<Integer> list;
    private List<Integer> subList;
    private Integer [] control;
    private Random random = new Random(1234);
    
    /**
     * Default constructor for test class SubListTest.
     */
    public SubListTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        control = new Integer[SIZE];
        for (int i = 0; i < SIZE; ++i)
        {
            control[i] = new Integer(random.nextInt(SIZE));
        }
        list = new ArrayList<Integer>(SIZE);
        resetListFromControl();
    }
    
    /**
     * Copies the original array of data back into the list.
     */
    protected void resetListFromControl()
    {
        list.clear();
        for (int i = 0; i < SIZE; ++i)
        {
            list.add(control[i]);
        }
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
     * Tests good constructor calls (should not throw, correct size).
     */
    public void testCreatingGood()
    {
        // test creating all valid sub-lists
        for (int i = 0; i <= SIZE; ++i)
        {
            for (int j = i; j <= SIZE; ++j)
            {
                subList = list.subList(i, j);
                assertNotNull("Hint: a good sublist creation was null",
                    subList);
                assertEquals("Hint: the sublist size was wrong",
                    j - i, subList.size());
            }
        }
    }

    /**
     * Tests bad constructor calls.
     */
    public void testCreatingBad()
    {
        // test creating several invalid sub-lists
        int [][] dataPoints = new int [][] {
            {-1, 0},              // negative starting index
            {0, -1},              // negative ending index
            {SIZE + 1, SIZE + 1}, // indices too big
            {1, 0},               // start after end
            {SIZE, SIZE - 1}      // start after end
        };
        
        for (int i = 0; i < dataPoints.length; ++i)
        {
            try
            {
                subList = list.subList(dataPoints[i][0], dataPoints[i][1]);
                fail("Hint: Sublist constructor should have thrown " +
                    "IllegalArgumentException or IndexOutOfBoundsException");
            }
            catch (IllegalArgumentException e)
            {
                assertTrue(true);
            }
            catch (IndexOutOfBoundsException e)
            {
                assertTrue(true);
            }
        }
    }
    
    /**
     * Tests iterating through a sublist in the forward direction.
     */
    public void testForwardSubListIteration()
    {
        // test all sublists
        for (int i = 0; i <= SIZE; ++i)
        {
            for (int j = i; j <= SIZE; ++j)
            {
                subList = list.subList(i, j);
                ListIterator<Integer> itr = subList.listIterator();
                for (int k = i; k < j; ++k)
                {
                    assertEquals("Hint: sublist contents did not match " +
                        "original list in forward iteration",
                        control[k], itr.next());
                }
                assertFalse("Hint: hasNext should have been false",
                    itr.hasNext());
            }
        }
    }

    /**
     * Tests iterating through a sublist in the backwards direction.
     */
    public void testBackwardSubListIteration()
    {
        // test all sublists
        for (int i = 0; i <= SIZE; ++i)
        {
            for (int j = i; j <= SIZE; ++j)
            {
                subList = list.subList(i, j);
                ListIterator<Integer> itr = 
                    subList.listIterator(subList.size());
                for (int k = j - 1; k >= i; --k)
                {
                    assertEquals("Hint: sublist contents did not match " +
                        "original list in reverse iteration",
                        control[k], itr.previous());
                }
                assertFalse("Hint: hasPrevious should have been false",
                    itr.hasPrevious());
            }
        }
    }
    
    /**
     * Tests removing (clearing) elements from a sublist.  Should affect
     * the original lists.  This can take a while on slow processors.
     */
    public void testSubListRemoves()
    {
        for (int i = 0; i <= SIZE; ++i)
        {
            for (int j = i; j <= SIZE; ++j)
            {
                subList = list.subList(i, j);
                subList.clear();
                assertEquals("Hint: clearing sublist didn't affect the " +
                    "original size properly", SIZE - j + i, list.size());
                
                // check contents
                ListIterator<Integer> itr = list.listIterator();
                for (int k = 0; k < SIZE; ++k)
                {
                    if (k < i || k >= j) 
                    {
                        assertEquals("Hint: removed wrong elements of " +
                            "original list", control[k], itr.next());
                    }
                }
                assertFalse("Hint: hasNext should have been false",
                    itr.hasNext());
                resetListFromControl();
            }
        }
    }

    /**
     * Tests adding elements in a sublist.
     */
    public void testSubListAdds()
    {
        Integer sentinel = new Integer(SIZE + 1);
        
        for (int i = 0; i <= SIZE; ++i)
        {
            subList = list.subList(i, i);
            subList.add(sentinel);
            assertEquals("Hint: adding to subList should have added to " +
                "original list size", SIZE + 1, list.size());
            
            // check contents
            ListIterator<Integer> itr = list.listIterator();

            for (int k = 0; k <= SIZE; ++k)
            {
                if (k < i)
                {
                    assertEquals("Hint: added in wrong location",
                        control[k], itr.next());
                }
                else if (k > i)
                {
                    assertEquals("Hint: added in wrong location",
                        control[k - 1], itr.next());
                }
                else
                {
                    assertEquals("Hint: added in wrong location",
                        sentinel, itr.next());
                }
                
            }
            assertFalse("Hint: hasNext should have been false", itr.hasNext());
            resetListFromControl();
        }
    }
    
    /**
     * Tests to see if sub-lists of sub-lists work properly.  This can take
     * a long time (upwards of 5 seconds on a 2 GHz machine).
     */
    public void testSubListSubList()
    {
        // test creating all valid sub-lists
        for (int i = 0; i <= SIZE; ++i)
        {
            for (int j = i; j <= SIZE; ++j)
            {
                subList = list.subList(i, j);
                int subSize = subList.size();
                for (int k = 0; k <= subSize; ++k)
                {
                    // pick a random stopping point
                    int m = subSize == k ? k : k + random.nextInt(subSize - k);
                    List<Integer> subSubList = subList.subList(k, m);
                    
                    assertNotNull("Hint: sub-list of sub-list was null",
                        subSubList);
                    assertEquals("Hint: sub-list sub-list size was wrong",
                        m - k, subSubList.size());
                        
                    subSubList.clear();
                    
                    assertEquals("Hint: original list size was wrong after " +
                        "clearing sub-list of sub-list",
                        SIZE - (m - k), list.size());
                        
                    resetListFromControl();
                    subList = list.subList(i, j);
                }
            }
        }
    }
}
