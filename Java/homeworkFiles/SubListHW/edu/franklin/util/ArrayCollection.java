package edu.franklin.util;

/**
 * A concrete implementation of Collection that is based on arrays.
 * Since we inherit from AbstractCollection, there are only a few methods
 * to implement such as <tt>add</tt>, <tt>iterator</tt>, and <tt>size</tt>
 * @param <E> the type of data to hold in the collection
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class ArrayCollection<E> extends AbstractCollection<E>
{
    private int next;
    private E[] data;
    
    /**
     * Constructs an empty collection with an initial capacity of 10.
     */
    public ArrayCollection()
    {
        this(10);
    }
    
    /**
     * Constructs an empty collection with the specified initial
     * capacity.
     * @param capacity the number of array elements to allocate initially
     * @throws IllegalArgumentException if the specified initial
     * capacity is negative
     */
    public ArrayCollection(int capacity)
    {
        if (capacity < 0)
        {
            throw new IllegalArgumentException("capacity cannot be negative");
        }

        this.next = 0;
        this.data = Utilities.resizeArray(this.data, capacity);
    }
    
    /**
     * Expands the array to 50% larger plus 1.
     */
    private void grow()
    {
        int newCapacity = (this.data.length * 3) / 2 + 1;
        this.data = Utilities.resizeArray(this.data, newCapacity);
    }
    
    /**
     * Inserts an element into a collection.  Returns true if the collection
     * is changed as a result of the operation, and false otherwise.
     * @param obj the object to add into the collection
     * @return true if the collection is altered
     */
    public boolean add(E obj)
    {
        if (this.next >= this.data.length)
        {
            grow();
        }
        
        this.data[this.next++] = obj;
        return true;
    }
    
    /**
     * Returns the number of elements in the collection.
     * @return the number of elements in the collection.
     */
    public int size()
    {
        return this.next;
    }
    
    /**
     * Removes all elements from the collection.  The collection will
     * be empty after calling this method.
     */
    @Override
    public void clear()
    {
        for (int i = 0; i < this.next; ++i)
        {
            this.data[i] = null;
        }
        this.next = 0;
    }
    
    /**
     * Generate an iterator object.
     * @return an iterator object.
     */
    public Iterator<E> iterator()
    {
        return new ArrayCollectionIterator();
    }
    
    /**
     * An implementation of the iterator interface.  Since iterators are
     * tied closely to the implementation, each collection should provide
     * their own iterator.
     */
    private class ArrayCollectionIterator implements Iterator<E>
    {
        private int cursor;
        private boolean nextCalled;
        
        /**
         * Constructs an iterator starting at element 0.
         */
        public ArrayCollectionIterator()
        {
            this.cursor = 0;
            this.nextCalled = false;
        }
        /**
         * Returns true when the iterator has more data to return.
         * @return true if the iterator has more data
         */        
        public boolean hasNext()
        {
            return cursor < ArrayCollection.this.next;
        }

        /**
         * Returns the next element from the iteration.
         * @return the next data element
         * @throw NoSuchElemenetException if there is no more data
         */   
        public E next()
        {
            if (!hasNext())
            {
                throw new java.util.NoSuchElementException(
                    "Beyond end of collection");
            }
            this.nextCalled = true;
            return ArrayCollection.this.data[this.cursor++];
        }

        /**
         * Removes the previously returned element from the iteration.
         * @throws IllegalStateException when next is not called immediately
         * before remove.
         */        
        public void remove()
        {
            if (!nextCalled)
            {
                throw new IllegalStateException("call to remove before next");
            }
            for (int i = cursor - 1; i < ArrayCollection.this.next - 1; ++i)
            {
                ArrayCollection.this.data[i] = ArrayCollection.this.data[i + 1];
            }
            
            // having removed the data, need to back up the cursor
            // and drop off the last element of the collection.
            ArrayCollection.this.data[--ArrayCollection.this.next] = null;
            --this.cursor;
            this.nextCalled = false;
        }
    }
}
