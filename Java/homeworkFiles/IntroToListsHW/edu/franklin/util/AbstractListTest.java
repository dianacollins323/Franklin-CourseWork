package edu.franklin.util;
import edu.franklin.misc.DataGenerator;
import edu.franklin.misc.IntegerGenerator;

/**
 * Tests for functionality defined in AbstractList.  This class is
 * itself abstract, as it relies on a subclass to define the method
 * <tt>getList</tt> that will return a concrete implementation.
 *
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public abstract class AbstractListTest extends AbstractCollectionTest
{
    /**
     * Default constructor for test class AbstractListTest.
     */
    public AbstractListTest()
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
     * This method should be overridden in derived classes such that it
     * will return a concrete implementation of List based on the
     * data provided in the generator.
     * @param <E> the type of data held in the collection
     * @param generator the generator which produces the data
     * @return a concrete list of the right type containing
     * the data from the generator
     */
    public abstract <E> List<E> getList(DataGenerator<E> generator);
        
    /**
     * Creates an ArrayList containing data from the specified
     * generator.
     * @param <E> the type of data to generate
     * @param generator the object which will provide the data
     * @return an ArrayList containing the data
     */
    public <E> Collection<E> getCollection(DataGenerator<E> generator)
    {
        return getList(generator);
    }

    /**
     * Test the indexOf method (searching).
     */
    public void testIndexOf()
    {
        List<Integer> list = getList(sequenceGenerator);
        
        for (int i = 0; i < list.size(); ++i)
        {
            assertEquals(i, list.indexOf(new Integer(i)));
        }
        
        for (int i = list.size(); i < list.size() * 2; ++i)
        {
            assertTrue(list.indexOf(new Integer(i)) < 0);
        }
    }
    
    /**
     * Test adding elements at a specified index.
     */
    public void testIteratorAddAtPosition()
    {
        List<Integer> list = getList(sequenceGenerator);
        int counter = 0;
        int elementToAdd = 99;
        
        for (counter = 0; counter < list.size(); counter += 2)
        {
            ListIterator<Integer> itr = list.listIterator(counter);
            itr.add(new Integer(elementToAdd));
            assertEquals(counter, list.indexOf(new Integer(elementToAdd)));
            --elementToAdd;
        }
    }
    
    /**
     * Test the list iterator nextIndex method.
     */
    public void testIteratorNextIndex()
    {
        List<Integer> list = getList(sequenceGenerator);
        
        int counter = 0;
        for (ListIterator<Integer> itr = list.listIterator(); itr.hasNext(); )
        {
            assertEquals(counter, itr.nextIndex());
            ++counter;
            itr.next();
        }
    }

    /**
     * Test the list iterator previousIndex method.
     */
    public void testIteratorPreviousIndex()
    {
        List<Integer> list = getList(sequenceGenerator);
        
        int counter = 0;
        for (ListIterator<Integer> itr = list.listIterator(); itr.hasNext(); )
        {
            itr.next();
            assertEquals(counter, itr.previousIndex());
            ++counter;
        }
    }
    
    /**
     * Test the iterator set method while iterating forward.
     */
    public void testIteratorSet()
    {
        /*# insert your code here for Homework 07 */
    }

    /**
     * Test the iterator hasPrevious method.
     */
    public void testIteratorHasPrevious()
    {
        List<Integer> list = getList(sequenceGenerator);
        
        for (int i = 1; i <= list.size(); ++i)
        {
            assertTrue(list.listIterator(i).hasPrevious());
        }
        assertFalse(list.listIterator(0).hasPrevious());
    }
    
    /**
     * Test the iterator previous method for backward iteration.
     */
    public void testIteratorPrevious()
    {
        List<Integer> list = getList(sequenceGenerator);
        
        int counter = 1;
        ListIterator<Integer> itr = list.listIterator(list.size());
        while (itr.hasPrevious())
        {
            assertEquals(list.size() - counter, itr.previous().intValue());
            ++counter;
        }
        
        try
        {
            itr.previous();
            fail("Should have thrown NoSuchElementException");
        }
        catch (java.util.NoSuchElementException exception)
        {
            assertTrue(true);
        }
    }
    
    /**
     * Test the lastIndexOf method (searching backward).
     */
    public void testLastIndexOf()
    {
        //create list
        List<Integer> list = getList(new IntegerGenerator(5));
        AbstractList newList = (AbstractList) list;
        //add a specific Integer object to a specific index
        newList.add(3, new Integer(17));
        //test list was created and Integer object was added
        assertEquals(6, newList.size());
        //test Integer object found and correct index returned
        assertEquals(3, newList.lastIndexOf(17));
        //add a same Integer object to another index
        newList.add(5, new Integer(17));
        //test list was created and Integer object was added
        assertEquals(7, newList.size());
        //test Integer object found and correct index returned
        assertEquals(5, newList.lastIndexOf(17));
        //test Integer object not found
        assertEquals(-1, newList.lastIndexOf(150));
    }

    /**
     * Test the get method to extract elements at a specified index.
     */
    public void testListGetAtIndex()
    {
        List<Integer> list = getList(sequenceGenerator);
        
        for (int i = 0; i < list.size(); ++i)
        {
            assertEquals(i, list.get(i).intValue());
        }
        
        try
        {
            list.get(-1);
            fail("Should have thrown IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertTrue(true);
        }
        
        try
        {
            list.get(list.size());
            fail("Should have thrown IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertTrue(true);
        }
    }
    
    /**
     * Test adding elements at a specified index.
     */
    public void testAddAtIndex()
    {
        List<Integer> list1 = getList(emptyGenerator);
        List<Integer> list2 = getList(sequenceGenerator);
        
        // put in the evens all in a row
        for (int i = 0; i < list2.size(); i += 2)
        {
            list1.add(list1.size(), new Integer(i));
        }
        
        // put in the odds between the evens
        for (int i = 1; i < list2.size(); i += 2)
        {
            list1.add(i, new Integer(i));
        }
        assertEquals(list2, list1);
    }
    
    /**
     * Test adding the contents of an entire collection at a specified index.
     */
    public void testAddAllAtIndex()
    {
        //generate 3 lists for testing
        List<Integer> list = getList(new IntegerGenerator(10));
        List<Integer> list2 = getList(emptyGenerator);
        List<Integer> list3 = getList(new IntegerGenerator(5));
        //test lists are created
        assertEquals(10, list.size());
        assertEquals(0, list2.size());
        assertEquals(5, list3.size());
        //cast list2 as AbstractList
        AbstractList thisList = (AbstractList) list2;
        //add list to thisList
        assertEquals(true, thisList.addAll(0, list));
        //test list was added to thisList
        assertEquals(10, thisList.size());
        //add list3 to thisList at index 11 - indexOutOfBounds
        assertEquals(false, thisList.addAll(11, list3));
        //test list3 was not added to thisList
        assertEquals(10, thisList.size());
    }
    
    /**
     * Test removing a single element at a specified index.
     */
    public void testRemoveAtIndex()
    {
        List<Integer> list = getList(sequenceGenerator.reset());
        List<Integer> control = getList(sequenceGenerator.reset());
        list.add(0, new Integer(99));
        list.add(6, new Integer(98));
        list.add(list.size(), new Integer(97));
        
        assertEquals(97, list.remove(list.size() - 1).intValue());
        assertEquals(98, list.remove(6).intValue());
        assertEquals(99, list.remove(0).intValue());
        
        assertEquals(control, list);
        
        try
        {
            list.remove(-1);
            fail("Should have thrown");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertTrue(true);
        }

        try
        {
            list.remove(list.size());
            fail("Should have thrown");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertTrue(true);
        }
    }
    
    /**
     * Test replacing an element at a specified index.
     */
    public void testListSetAtIndex()
    {
        List<Integer> list = getList(sequenceGenerator);
        
        for (int i = 0; i < list.size(); i += 2)
        {
            list.set(i, new Integer(100 + i));
        }
        
        for (int i = 0; i < list.size(); i += 2)
        {
            // This test fails until the list iterator set method is written
            assertEquals(100 + i, list.get(i).intValue());
        }
        for (int i = 1; i < list.size(); i += 2)
        {
            assertEquals(i, list.get(i).intValue());
        }
    }
    
    /**
     * Test co-modification: next after remove.
     */
    public void testForCoMods1()
    {
        List<Integer> list = getList(sequenceGenerator);
        ListIterator<Integer> itr1 = list.listIterator();
        ListIterator<Integer> itr2 = list.listIterator();
        
        itr1.next();
        itr1.remove();
        
        try
        {
            itr2.next();
            fail("Should have thrown ConcurrentModificationException");
        }
        catch (java.util.ConcurrentModificationException e)
        {
            assertTrue(true);
        }
    }

    /**
     * Test co-modification: previous after remove.
     */
    public void testForCoMods2()
    {
        List<Integer> list = getList(sequenceGenerator);
        ListIterator<Integer> itr1 = list.listIterator();
        ListIterator<Integer> itr2 = list.listIterator();
        
        itr1.next();
        itr2.next();
        itr2.remove();
        
        try
        {
            itr1.previous();
            fail("Should have thrown ConcurrentModificationException");
        }
        catch (java.util.ConcurrentModificationException e)
        {
            assertTrue(true);
        }
    }

    /**
     * Test co-modification: add after add.
     */
    public void testForCoMods3()
    {
        List<Integer> list = getList(sequenceGenerator);
        ListIterator<Integer> itr1 = list.listIterator();
        ListIterator<Integer> itr2 = list.listIterator();
        
        itr1.add(new Integer(99));
        
        try
        {
            itr2.add(new Integer(99));
            fail("Should have thrown ConcurrentModificationException");
        }
        catch (java.util.ConcurrentModificationException e)
        {
            assertTrue(true);
        }
    }

    /**
     * Test co-modification: remove after remove.
     */
    public void testForCoMods4()
    {
        List<Integer> list = getList(sequenceGenerator);
        ListIterator<Integer> itr1 = list.listIterator();
        ListIterator<Integer> itr2 = list.listIterator();
        
        itr1.next();
        itr2.next();
        itr2.remove();
        
        try
        {
            itr1.remove();
            fail("Should have thrown ConcurrentModificationException");
        }
        catch (java.util.ConcurrentModificationException e)
        {
            assertTrue(true);
        }
    }
}
