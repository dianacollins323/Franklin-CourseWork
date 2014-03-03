package edu.franklin.util;

/**
 * A concrete implementation of Collection that is based on a singly linked
 * list. Since we inherit from AbstractCollection, there are only a few
 * methods to implement such as <tt>add</tt>, <tt>iterator</tt>, and
 * <tt>size</tt>.
 * 
 * @param <E> the type of data to hold in the collection
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public class LinkedCollection<E> extends AbstractCollection<E>
{
    // count of Link nodes in the list
    private int length = 0;
    // link to the first node in the list
    private Link<E> head;
    // link to the last node in the list
    private Link<E> tail;

    /**
     * Returns the number of elements in the collection.
     * @return the number of elements in the collection.
     */
    public int size()
    {
        return this.length;
    }
    
//     /**
//      * Inserts an element into a collection.  Returns true if the
//      * collection is changed as a result of the operation, and false
//      * otherwise.
//      * @param obj the object to add into the collection
//      * @return true if the collection is altered
//      */    
//     public boolean add(E obj)
//     {
//         Link<E> link = new Link<E>(obj, null);
//         
//         // there are two cases to consider:
//         // 1.  adding the first link into the list
//         // 2.  adding all subsequent links into the list
//         
//         /*# insert your code here to add in the newly created link */
//         
//         ++this.length;
//         return true;
//     }
    
    /**
     * Inserts an element into a collection.  Returns true if the
     * collection is changed as a result of the operation, and false
     * otherwise.
     * @param obj the object to add into the collection
     * @return true if the collection is altered
     */    
    public boolean add(E obj)
    {
        Link<E> link = new Link<E>(obj, null);
        
        // there are two cases to consider:
        // 1.  adding the first link into the list
        // 2.  adding all subsequent links into the list

        if (this.tail == null)
        {
            // first element to be added to the list
            this.head = link;
        }
        else
        {
            // append to the end of existing list
            this.tail.next = link;
        }
        this.tail = link;
        
        ++this.length;
        return true;
    }

    /**
     * Generate an iterator object.
     * @return an iterator object.
     */    
    public Iterator<E> iterator()
    {
        return new LinkedCollectionIterator();
    }
    
    /**
     * Node structure within the singly linked list.  A node consists of
     * a reference to a data item, and a reference to the next node
     * in the chain.  A <tt>null</tt> reference in <tt>next</tt> indicates
     * the end of the list.
     */
    private static final class Link<E>
    {
        // reference to the data in the node
        private E data;
        // reference to the next node in the chain
        private Link<E> next;
        
        /**
         * Construct a Link node with the given data element and the
         * given next link in the chain.
         * @param element the data to hold in the node
         * @param nextLink a reference to the next link
         */
        private Link(E element, Link<E> nextLink)
        {
            this.data = element;
            this.next = nextLink;
        }
    }

    /**
     * An implementation of the iterator interface.  Since iterators are
     * tied closely to the implementation, each collection should provide
     * their own iterator.
     */
    private class LinkedCollectionIterator implements Iterator<E>
    {
        private Link<E> cursor = null;
        private Link<E> priorCursor = null;
        private boolean nextCalled = false;
        
        /**
         * Returns true when the iterator has more data to return.
         * @return true if the iterator has more data
         */
        public boolean hasNext()
        {
            if (this.cursor != null)
            {
                // there has been a prior call to next; can we advance?
                return this.cursor.next != null;
            }
            // no prior call to next; does the list have any elements?
            return LinkedCollection.this.head != null;
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
            this.priorCursor = this.cursor;

            // There are two cases to consider:
            // 1.  The first time next is called
            // 2.  All subsequent calls to next

            this.cursor = this.cursor == null ?
                LinkedCollection.this.head : this.cursor.next;

            this.nextCalled = true;
            return this.cursor.data;
        }
        
        /**
         * Removes the previously returned element from the iteration.
         * @throws IllegalStateException when next is not called immediately
         * before remove.
         */
        public void remove()
        {
            if (!this.nextCalled)
            {
                throw new IllegalStateException(
                    "call to remove before next");
            }

            // There are four cases to consider:
            // 1.  Removing the head node of the list
            // 2.  Removing the tail node of the list
            // 3.  Removing the head and tail node (i.e. the only node)
            // 4.  Removing a node in the middle of the list

            /*# insert your code here for Homework 07 */
            
            this.cursor = this.priorCursor;
            --LinkedCollection.this.length;
            this.nextCalled = false;
        }
    }
}
