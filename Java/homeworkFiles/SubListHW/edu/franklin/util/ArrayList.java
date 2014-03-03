package edu.franklin.util;

/**
 * A concrete implementation of List that is based on arrays.
 * Since we inherit from AbstractList, there are only a few methods
 * to implement such as <tt>size</tt>, <tt>listIterator</tt>, and
 * <tt>subList</tt>.  However ArrayLists also provide methods
 * <tt>ensureCapacity</tt> and<tt>trimToSize</tt> that are unique
 * to array based implementations.
 * 
 * @param <E> the type of data to hold in the collection
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class ArrayList<E> extends AbstractList<E>
{
    // the array backing the list
    private E[] data;
    
    // the number of elements of the array that are consumed with data
    private int usedSlots;
    
    /**
     * Constructs an empty list with an initial capacity of 10.
     */
    public ArrayList()
    {
        this(10);
    }
    /**
     * Constructs an empty list with the specified initial
     * capacity.
     * @param capacity the number of array elements to allocate initially
     * @throws IllegalArgumentException if the specified initial
     * capacity is negative
     */
    public ArrayList(int capacity)
    {
        if (capacity < 0)
        {
            throw new IllegalArgumentException("capacity cannot be negative");
        }
        this.usedSlots = 0;
        this.data = Utilities.resizeArray(this.data, capacity);
    }
    
    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list.
     */
    public int size()
    {
        return this.usedSlots;
    }

    /**
     * Removes all elements from the collection.  The collection will
     * be empty after calling this method.  A faster implementation than
     * can be done in the AbstractCollection class.
     */
    @Override
    public void clear()
    {
        for (int i = 0; i < this.size(); ++i)
        {
            data[i] = null;
        }
        this.usedSlots = 0;
        ++this.modCount;
    }
//     /**
//      * Create a sublist of this list between the given indices.  The sublist
//      * is not a copy of the elements of this list, but rather a restricted
//      * view on them.  Thus, any insertions, deletions, or replacements
//      * of elements in the sublist will affect the original list.
//      * 
//      * WARNING!!!  This implementation does not use the "view" philosophy.
//      * Any changes to the sublist will <b>not</b> be reflected in the
//      * original list.
//      * 
//      * @param fromIndex the starting index (inclusive) of the sublist
//      * @param toIndex the ending index (exclusive) of the sublist
//      * @return a restricted view on this list
//      * @throws IndexOutOfBoundsException if either index is < 0 or > size.
//      */
//     public List<E> subList(int fromIndex, int toIndex)
//     {
//         List<E> result = new ArrayList<E>(toIndex - fromIndex);
//         ListIterator<E> itr = this.listIterator(fromIndex);
//         while (fromIndex < toIndex)
//         {
//             result.add(itr.next());
//             ++fromIndex;
//         }
//         return result;
//     }

    /**
     * Returns a ListIterator over the elements in this list.  The starting
     * position for the list iterator will be the 0th element.
     * @return a ListIterator over the elements in this list.
     */
    public ListIterator<E> listIterator(int position)
    {
        return new ArrayListIterator(position);
    }
    
    /**
     * Ensures that the backing array has enough capacity to contain at
     * least <tt>requiredCapacity</tt> items.  If not, then it will
     * expand the capacity by the larger of either the required capacity
     * or 50% more + 1.
     * @param requiredCapacity the desired capacity
     */
    public void ensureCapacity(int requiredCapacity)
    {
        ++modCount;
        if (requiredCapacity > this.data.length)
        {
            int calculatedCapacity = (this.data.length * 3) / 2 + 1;
            if (calculatedCapacity < requiredCapacity)
            {
                calculatedCapacity = requiredCapacity;
            }
            this.data = Utilities.resizeArray(this.data, calculatedCapacity);
        }
    }
    
    /**
     * Trims the excess capacity off of the backing array, reducing it
     * to size() in length.
     */
    public void trimToSize()
    {
        ++modCount;
        this.data = Utilities.resizeArray(this.data, this.size());
        this.usedSlots = this.data.length;
    }
    
    /**
     * An implementation of the ListIterator interface.  Since iterators are
     * tied closely to the implementation, each list should provide
     * its own iterator.
     */
    private class ArrayListIterator implements ListIterator<E>
    {
        // our current position in the array
        private int cursor;
        
        // the previous position in the array
        private int priorCursor;
        
        // the expected number of modifications to the list, if this
        // iterator is making all the modifications
        private int expectedModCount;
        
        // a flag that will indicate that next or previous was not called
        // immediately before an operation
        private static final int BAD_POSITION = -1;
        
        /**
         * Constructs an iterator starting at the specified position.
         * @param position where to start the iteration
         * @throws IndexOutOfBoundsException if the specified position is
         * not within [0...size()]
         */
        public ArrayListIterator(int position)
        {
            if (position < 0 || position > size())
            {
                throw new java.lang.IndexOutOfBoundsException("" + position +
                    " is out of bounds [0..." + size() + "]");
            }
            this.cursor = position;
            this.priorCursor = BAD_POSITION;
            synchronizeModCounts();
        }
        
        /**
         * Returns true when the iterator has more data to return.
         * @return true if the iterator has more data
         */
        public boolean hasNext()
        {
            return this.cursor < size();
        }

        /**
         * Returns true when the iterator has more data to return in the
         * reverse direction.
         * @return true if the iterator has more data
         */
        public boolean hasPrevious()
        {
            return this.cursor > 0;
        }

        /**
         * Returns the next element from the iteration.
         * @return the next data element
         * @throws java.util.NoSuchElementException when there is no next
         */
        public E next()
        {
            checkForComodification();
            if (!hasNext())
            {
                throw new java.util.NoSuchElementException(
                    "Beyond end of list");
            }
            this.priorCursor = this.cursor;
            return ArrayList.this.data[this.cursor++];
        }
        
        /**
         * Returns the index of the element that would be returned should
         * a subsequent call to <tt>previous</tt> be made.
         * @return the index of the previous element
         * @throws java.util.NoSuchElementException when there is no previous
         */
        public E previous()
        {
            checkForComodification();
            if (!hasPrevious())
            {
                throw new java.util.NoSuchElementException(
                    "Before beginning of list");
            }
            this.priorCursor = --this.cursor;
            return ArrayList.this.data[this.cursor];
        }
        
        /**
         * Removes the previously returned element from the iteration.
         * @throws IllegalStateException when next or previous is not called
         * immediately before remove.
         */
        public void remove()
        {
            // make sure nothing has been altered under us
            checkForComodification();
            
            // make sure there was a prior call to next or previous
            checkForValid();
            
            // move all data one element to the left
            for (int i = this.priorCursor; i < size() - 1; ++i)
            {
                ArrayList.this.data[i] = ArrayList.this.data[i + 1];
            }
            
            // drop last element copy
            ArrayList.this.data[size() - 1] = null;
            
            // freed up one element of backing array
            --ArrayList.this.usedSlots;
            
            // update our position for further iteration
            this.cursor = this.priorCursor;
            
            // set flag for no prior previous or next call
            this.priorCursor = BAD_POSITION;
            
            // underlying array has changed
            ++modCount;
            
            // synchronize with this iterator
            synchronizeModCounts();
        }
        
        /**
         * Inserts the specified element into the list at the present iterator
         * position.  The insertion point is before the element that would be
         * returned by a call to <tt>next</tt> and after the element that would
         * be returned by a call to <tt>previous</tt>.  After adding the
         * element, a call to <tt>previous</tt> will return the newly inserted
         * element, but a call to <tt>next</tt> will return the element
         * immediately after the one inserted.
         * @param obj the element to insert
         */
        public void add(E obj)
        {
            // make sure nothing has been altered under us
            checkForComodification();
            
            // make sure there is enough space for one more element
            ensureCapacity(size() + 1);
            
            // move all the data one element to the right
            for (int i = size(); i > cursor; --i)
            {
                ArrayList.this.data[i] = ArrayList.this.data[i - 1];
            }
            
            // keep track of where we were
            this.priorCursor = BAD_POSITION;
            
            // store data and advance beyond added element
            ArrayList.this.data[this.cursor++] = obj;
            
            // used one more element of the backing array
            ++ArrayList.this.usedSlots;
            
            // re-synchronize the mod counts
            synchronizeModCounts();
        }
        
        /**
         * Returns the index of the element that would be returned should
         * a subsequent call to <tt>next</tt> be made.
         * @return the index of the next element
         */
        public int nextIndex()
        {
            return this.cursor;
        }

        /**
         * Returns the index of the element that would be returned should
         * a subsequent call to <tt>previous</tt> be made.
         * @return the index of the previous element
         */        
        public int previousIndex()
        {
            return this.cursor - 1;
        }
        
        /**
         * Replaces the last element returned by either <tt>next</tt> or
         * <tt>previous</tt>.  Cannot be called immediately after <tt>add</tt>
         * or <tt>remove</tt>.
         * @param obj the object with which to replace the one returned
         * earlier.
         */
        public void set(E obj)
        {
            checkForComodification();
            checkForValid();
            
            // whatever we handed back last is what we overwrite
            ArrayList.this.data[priorCursor] = obj;
        }
        
        /**
         * Ensures that the expected and actual modification counts between
         * the list and the iterator are in sync with one another.
         * @throws java.util.ConcurrentModificationException when they aren't
         */
        protected void checkForComodification()
        {
            if (this.expectedModCount != ArrayList.this.modCount)
            {
                throw new java.util.ConcurrentModificationException();
            }
        }
        
        /**
         * Ensures that there was a prior call to either next or previous
         * before performing an operation on the last returned item.
         * @throws IllegalStateException when there was no prior call
         */
        protected void checkForValid()
        {
            if (this.priorCursor == BAD_POSITION)
            {
                throw new IllegalStateException(
                    "No prior call to next() or previous()");
            }
        }
        
        /**
         * Re-synchronizes the modification counts between the list
         * and the iterator.
         */
        protected void synchronizeModCounts()
        {
            this.expectedModCount = ArrayList.this.modCount;
        }
    }
}
