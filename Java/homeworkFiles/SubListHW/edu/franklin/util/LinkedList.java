package edu.franklin.util;

/**
 * A concrete implementation of List that is based on links.
 * Since we inherit from AbstractList, there are only a few methods
 * to implement such as <tt>size</tt>, <tt>listIterator</tt>, and
 * <tt>subList</tt>.  But, it also defines methods unique to LinkedList
 * such as <tt>addFirst</tt> and <tt>addLast</tt>.
 * 
 * @param <E> the type of data to hold in the collection
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class LinkedList<E> extends AbstractList<E>
{
    /** the dummy header link. */
    private Link<E> header;
    /** the number of links in the list (excluding the header). */
    private int length;
    
    /**
     * Constructs an empty list.
     */
    public LinkedList()
    {
        // establish a dummy header node
        this.header = new Link<E>(null, null, null);
        // connect the header to itself
        this.header.previous = this.header;
        this.header.next = this.header;
        // don't count the header in the length
        this.length = 0;
    }
    
    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list.
     */
    public int size()
    {
        return this.length;
    }

    /**
     * Returns a ListIterator over the elements in this list.  The starting
     * position for the list iterator will be the 0th element.
     * @return a ListIterator over the elements in this list.
     */
    public ListIterator<E> listIterator(int index)
    {
        return new LinkedListIterator(index);
    }

//     /**
//      * Adds an element to the front of the list.
//      * @param obj the element to add
//      */
//     public void addFirst(E obj)
//     {
//         /*# insert your code here */
//     }
//     
//     /**
//      * Adds an element to the front of the list.
//      * @param obj the element to add
//      */
//     public void addLast(E obj)
//     {
//         /*# insert your code here */
//     }

    /**
     * Adds an element to the front of the list.
     * @param obj the element to add
     */
    public void addFirst(E obj)
    {
        this.header.append(obj);
        ++this.length;
        ++this.modCount;
    }
    
    /**
     * Adds an element to the front of the list.
     * @param obj the element to add
     */
    public void addLast(E obj)
    {
        this.header.prepend(obj);
        ++this.length;
        ++this.modCount;
    }

    /**
     * Returns the first element of the list.
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if the list is empty.
     */
    public E getFirst()
    {
        if (this.size() == 0)
        {
            throw new java.util.NoSuchElementException("list is empty");
        }
        return this.header.next.data;
    }

    /**
     * Returns the last element of the list.
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if the list is empty.
     */
    public E getLast()
    {
        if (this.size() == 0)
        {
            throw new java.util.NoSuchElementException("list is empty");
        }
        return this.header.previous.data;
    }
    
    /**
     * Removes and returns the first element of the list.
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if the list is empty.
     */
    public E removeFirst()
    {
        E result = getFirst();
        remove(0);
        return result;
    }
    
    /**
     * Removes and returns the last element of the list.
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if the list is empty.
     */
    public E removeLast()
    {
        E result = getLast();
        remove(size() - 1);
        return result;
    }
    
    /**
     * Node structure within the doubly linked list.  A node consists of
     * a reference to a data item, and a reference to the next and previous
     * nodes in the chain.  The list will be circular as well, so neither
     * <tt>next</tt> nor <tt>previous</tt> should ever be <tt>null</tt>.
     */
    private static final class Link<E>
    {
        // reference to the data in the node
        private E data;
        // reference to the next node in the chain
        private Link<E> next;
        // reference to the previous node in the chain
        private Link<E> previous;
        
        /**
         * Constructs a link containing the specified data element
         * between the previous and next links given.
         * @param previousLink the link to which to attach backward
         * @param element the data to store in the link
         * @param nextLink the link to which to attach forward
         */
        public Link(Link<E> previousLink, E element, Link<E> nextLink)
        {
            this.previous = previousLink;
            this.next = nextLink;
            this.data = element;
        }
        
        /**
         * Removes this link from the linked list.  There is enough
         * information in the link class to now detach itself from
         * its connected links.
         */
        public void remove()
        {
            this.previous.next = this.next;
            this.next.previous = this.previous;
            this.data = null;
        }
        
        /**
         * Construct and attach a new link after this one, which
         * contains the specified data element.
         * @param element the data element to put in the new link
         * @return the newly attached link
         */
        public Link<E> append(E element)
        {
            Link<E> link = new Link<E>(this, element, this.next);
            this.next.previous = link;
            this.next = link;
            return link;
        }
        
        /**
         * Construct and attach a new link before this one, which
         * contains the specified data element.
         * @param element the data element to put in the new link
         * @return the newly attached link
         */
        public Link<E> prepend(E element)
        {
            Link<E> link = new Link<E>(this.previous, element, this);
            this.previous.next = link;
            this.previous = link;
            return link;
        }
    }

    /**
     * An implementation of the ListIterator interface.  Since iterators are
     * tied closely to the implementation, each list should provide
     * its own iterator.
     */
    private class LinkedListIterator implements ListIterator<E>
    {
        /** for fail-fast iterators. */
        private int expectedModCount;
        /** where data will be returned from next time. */
        private Link<E> cursor;
        /** where data was just returned from. */
        private Link<E> priorCursor;
        /** index of the cursor (since this isn't an array). */
        private int cursorIndex;
                
        /**
         * Constructs an iterator starting at the specified position.
         * @param position where to start the iteration
         * @throws IndexOutOfBoundsException if the specified position is
         * not within [0...size()]
         */        
        public LinkedListIterator(int position)
        {
            synchronizeModCounts();
            
            // see if they can build an iterator here
            if (position < 0 || position > size())
            {
                throw new java.lang.IndexOutOfBoundsException("" + position +
                    " is out of bounds [0..." + size() + "]");
            }
            
            if (position <= size() / 2)
            {
                // shortest path is forward
                this.cursor = LinkedList.this.header.next;
                for (int i = 0; i < position; ++i)
                {
                    this.cursor = this.cursor.next;
                }
            }
            else
            {
                // shortest path is backward
                this.cursor = LinkedList.this.header;
                for (int i = size(); i > position; --i)
                {
                    this.cursor = this.cursor.previous;
                }
            }
            this.cursorIndex = position;

            // set last returned to its invalid state
            this.priorCursor = null;
        }
        
        /**
         * Returns true when the iterator has more data to return.
         * @return true if the iterator has more data
         */
        public boolean hasNext()
        {
            return this.cursor != LinkedList.this.header;
        }
        
        /**
         * Returns the next element from the iteration.
         * @return the next data element
         */
        public E next()
        {
            checkForComodification();
            if (!hasNext())
            {
                throw new java.util.NoSuchElementException(
                    "Beyond end of list");
            }
            // keep track of where we were
            this.priorCursor = this.cursor;
            // advance to next
            this.cursor = this.cursor.next;
            // keep track of index position as we advance
            ++this.cursorIndex;
            // return the data we passed over
            return this.priorCursor.data;
        }
    
        /**
         * Removes the previously returned element from the iteration.
         * @throws IllegalStateException when next is not called
         * immediately before remove.
         */
        public void remove()
        {
            checkForComodification();
            checkForValid();
        
            // figure out where we need to place the iterator after removing
            if (this.priorCursor == this.cursor)
            {
                // prior call was to previous()
                this.cursor = this.cursor.next;
            }
            else
            {
                // prior call was to next()
                --this.cursorIndex;
            }
            
            // actually remove the element
            this.priorCursor.remove();

            // invalidate removes
            this.priorCursor = null;
            // keep length in sync with list
            --LinkedList.this.length;
            
            updateAndSynchronizeModCounts();
        }

//         /**
//          * Removes the previously returned element from the iteration.
//          * @throws IllegalStateException when next is not called
//          * immediately before remove.
//          */
//         public void remove()
//         {
//             checkForComodification();
//             checkForValid();
// 
//             /*# insert your code here */
//             
//             // keep length in sync with list
//             --LinkedList.this.length;
//             updateAndSynchronizeModCounts();
//          }

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
            checkForComodification();
            Link<E> link = this.cursor.prepend(obj);
            ++LinkedList.this.length;
            ++this.cursorIndex;
            updateAndSynchronizeModCounts();
        }
        
        /**
         * Returns true when the iterator has more data to return in the
         * reverse direction.
         * @return true if the iterator has more data
         */
        public boolean hasPrevious()
        {
            return this.cursor.previous != LinkedList.this.header;
        }

//         /**
//          * Returns true when the iterator has more data to return in the
//          * reverse direction.
//          * @return true if the iterator has more data
//          */
//         public boolean hasPrevious()
//         {
//             /*# insert your code here */
//             return false;
//         }
        
        /**
         * Returns the index of the element that would be returned should
         * a subsequent call to <tt>next</tt> be made.
         * @return the index of the next element
         */
        public int nextIndex()
        {
            return this.cursorIndex;
        }

//         /**
//          * Returns the index of the element that would be returned should
//          * a subsequent call to <tt>next</tt> be made.
//          * @return the index of the next element
//          */
//         public int nextIndex()
//         {
//             /*# insert your code here */
//             return 0;
//         }
        
        /**
         * Returns the element from the iteration when traversing in the
         * reverse direction.
         * @return the previous data element
         */
        public E previous()
        {
            checkForComodification();
            if (!hasPrevious())
            {
                throw new java.util.NoSuchElementException(
                    "Before beginning of list");
            }
            // keep track of where we were
            this.cursor = this.cursor.previous;
            // move backward
            this.priorCursor = this.cursor;
            // keep track of index position as we move back
            --this.cursorIndex;
            // return the data we passed over
            return this.priorCursor.data;
        }

//         /**
//          * Returns the element from the iteration when traversing in the
//          * reverse direction.
//          * @return the previous data element
//          */
//         public E previous()
//         {
//             checkForComodification();
//             if (!hasPrevious())
//             {
//                 throw new java.util.NoSuchElementException(
//                     "Before beginning of list");
//             }
//             
//             /*# insert your code here */
//             
//             return null;
//         }


        /**
         * Returns the index of the element that would be returned should
         * a subsequent call to <tt>previous</tt> be made.
         * @return the index of the previous element
         */
        public int previousIndex()
        {
            return this.cursorIndex - 1;
        }

//         /**
//          * Returns the index of the element that would be returned should
//          * a subsequent call to <tt>previous</tt> be made.
//          * @return the index of the previous element
//          */
//         public int previousIndex()
//         {
//             /*# insert your code here */
//             return 0;
//         }
        
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
            this.priorCursor.data = obj;
        }

//         /**
//          * Replaces the last element returned by either <tt>next</tt> or
//          * <tt>previous</tt>.  Cannot be called immediately after
//          * <tt>add</tt> or <tt>remove</tt>.
//          * @param obj the object with which to replace the one returned
//          * earlier.
//          */
//         public void set(E obj)
//         {
//             /*# insert your code here */
//         }
        
        /**
         * Ensures that the expected and actual modification counts between
         * the list and the iterator are in sync with one another.
         * @throws java.util.ConcurrentModificationException when they aren't
         */
        protected void checkForComodification()
        {
            if (this.expectedModCount != LinkedList.this.modCount)
            {
                throw new java.util.ConcurrentModificationException();
            }
        }
        
        /**
         * Re-synchronizes the modification counts between the list
         * and the iterator.
         */
        protected void synchronizeModCounts()
        {
            this.expectedModCount = LinkedList.this.modCount;
        }
        
        /**
         * Updates and then re-synchronizes the modification counts
         * between the list and the iterator.
         */
        protected void updateAndSynchronizeModCounts()
        {
            ++LinkedList.this.modCount;
            this.synchronizeModCounts();
        }
        
        /**
         * Ensures that there was a prior call to either next or previous
         * before performing an operation on the last returned item.
         * @throws IllegalStateException when there was no prior call
         */
        protected void checkForValid()
        {
            if (this.priorCursor == null)
            {
                throw new IllegalStateException(
                    "No prior call to next() or previous()");
            }
        }
    }
}
