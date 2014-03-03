package edu.franklin.util;

/**
 * A sublist is not a copy of the elements of a list, but rather
 * a restricted view on them.  Thus, any insertions, deletions,
 * or replacements of elements in the sublist will affect the
 * original list.  We do this be using the decorator pattern
 * on both the original list and the original list's iterator.
 * 
 * @param <E> the type of data held in the list
 * 
 * @author Todd A. Whittaker 
 * @version 2005-09
 */
public class SubList<E> extends AbstractList<E>
{
    /** the list to decorate. */
    private List<E> list;
    /** the starting index of the subList (inclusive). */
    private int start;
    /** the ending index of the subList (exclusive). */
    private int end;

    /**
     * Constructs a sub-list of the <tt>baseList</tt> parameter between
     * the given indices.
     * @param baseList the list to decorate
     * @param fromIndex the starting index of the sub-list (inclusive)
     * @param toIndex the ending index of the sub-list (exclusive)
     */
    public SubList(List<E> baseList, int fromIndex, int toIndex)
    {
        if (fromIndex < 0)
        {
            throw new IndexOutOfBoundsException("beginning index is not is less then 0");
        }
        else if (toIndex > baseList.size())
        {
            throw new IndexOutOfBoundsException("ending index is greater than size of base list");
        }
        else if (fromIndex > toIndex)
        {
            throw new IllegalArgumentException("beginnind index cannot be bigger than ending index");
        }
        else
        {
            this.list = baseList;
            this.start = fromIndex;
            this.end = toIndex;
        }
    }

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list.
     */
    public int size()
    {
        return this.end - this.start;
    }

    /**
     * Returns a sub-list of a sub-list.  To do this, we'll "re-decorate"
     * the original list passed into the constructor, but with adjusted
     * starting and ending indices.
     * 
     * @param fromIndex the starting index (inclusive) of the sublist
     * @param toIndex the ending index (exclusive) of the sublist
     * @return a restricted view of the list
     */
    public List<E> subList(int fromIndex, int toIndex)
    {
        return new SubList<E>(this.list, this.start + fromIndex, this.start + toIndex);
    }

    /**
     * Returns a ListIterator over the elements in this list.  The starting
     * position for the list iterator will be the 0th element.
     * @return a ListIterator over the elements in this list.
     */
    public ListIterator<E> listIterator(int index)
    {
        return new SubListIterator(index);
    }

    /**
     * An implementation of the ListIterator interface.  Since iterators are
     * tied closely to the implementation, each list should provide
     * its own iterator.
     */
    private class SubListIterator implements ListIterator<E>
    {
        /** the list iterator to decorate. */
        private ListIterator<E> iter;

        /**
         * Constructs an iterator starting at the specified position.
         * @param index where to start the iteration
         * @throws IndexOutOfBoundsException if the specified position is
         * not within [0...size()]
         */
        public SubListIterator(int index)
        {
            this.iter = list.listIterator(index + start);
        }

        /**
         * Returns true when the iterator has more data to return.
         * @return true if the iterator has more data
         */
        public boolean hasNext()
        {
            if (this.iter.nextIndex() < end)
            {
                return this.iter.hasNext();
            }
            else
            {
                return false;
            }
        }

        /**
         * Returns the next element from the iteration.
         * @return the next data element
         */
        public E next()
        {
            if (this.iter.nextIndex() < end)
            {
                return iter.next();    
            }
            else
            {
                return null;
            }
        }

        /**
         * Replaces the last element returned by either <tt>next</tt> or
         * <tt>previous</tt>.  Cannot be called immediately after
         * <tt>add</tt> or <tt>remove</tt>.
         * @param obj the object with which to replace the one returned
         * earlier.
         */
        public void set(E obj)
        {
            iter.set(obj);
        }

        /**
         * Inserts the specified element into the list at the present
         * iterator position.  The insertion point is before the element
         * that would be returned by a call to <tt>next</tt> and after
         * the element that would be returned by a call to
         * <tt>previous</tt>.  After adding the element, a call to
         * <tt>previous</tt> will return the newly inserted element,
         * but a call to <tt>next</tt> will return the element immediately
         * after the one inserted.
         * @param obj the element to insert
         */
        public void add(E obj)
        {
            iter.add(obj);
            end++;
        }

        /**
         * Removes the previously returned element from the iteration.
         * @throws IllegalStateException when next is not called
         * immediately before remove.
         */
        public void remove()
        {
            iter.remove();
            end--;
        }

        /**
         * Returns true when the iterator has more data to return in the
         * reverse direction.
         * @return true if the iterator has more data
         */
        public boolean hasPrevious()
        {
            if (this.iter.previousIndex() >= start)
            {
                return this.iter.hasPrevious();
            }
            else
            {
                return false;
            }
        }

        /**
         * Returns the element from the iteration when traversing in the
         * reverse direction.
         * @return the previous data element
         */
        public E previous()
        {
            if (this.iter.previousIndex() >= start)
            {
                return iter.previous();    
            }
            else
            {
                return null;
            }
        }

        /**
         * Returns the index of the element that would be returned should
         * a subsequent call to <tt>next</tt> be made.
         * @return the index of the next element
         */
        public int nextIndex()
        {
            int nextIndex = -1;
            if (iter.nextIndex() > start && iter.nextIndex() < end)
            {
                nextIndex = iter.nextIndex();
            }
            return nextIndex;
        }

        /**
         * Returns the index of the element that would be returned should
         * a subsequent call to <tt>previous</tt> be made.
         * @return the index of the previous element
         */
        public int previousIndex()
        {
            int previousIndex = -1;
            if (iter.previousIndex() >= start && iter.previousIndex() < end)
            {
                previousIndex = iter.previousIndex();
            }
            return previousIndex;
        }
    }
}
