package edu.franklin.util;

/**
 * The base class from which to derive List implementations.  A list is a
 * sequence of objects kept in a container.  Typical operations are to add,
 * remove, and iterate over the elements.  This class provides implementations
 * for all operations except <tt>size</tt>, and <tt>listIterator</tt> (though
 * the provided implementations may be inefficient for some concrete classes).
 * Many of these operations can (and should) be overridden in derived
 * classes to increase the efficiency of the operation.  In particular,
 * <tt>addAll</tt> is a good candidate for advanced collections.
 * 
 * @param <E> the type of data to hold in the collection
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public abstract class AbstractList<E> extends
    AbstractCollection<E> implements List<E>
{
    /** The number of times the collection has been structurally modified. */
    protected int modCount = 0;
    
    private static final int BAD_POSITION = -1;
    
    /**
     * Generate an iterator object starting at the beginning of the list.
     * @return an iterator object.
     */
    public Iterator<E> iterator()
    {
        return listIterator();
    }

    /**
     * Generate a list iterator (bi-directional) object starting at the
     * beginning of the list.
     * @return a list iterator object
     */
    public ListIterator<E> listIterator()
    {
        return listIterator(0);
    }
    
    /**
     * Inserts an element into a list at the end.  Returns true if the
     * collection is changed as a result of the operation, and false
     * otherwise.
     * @param obj the object to add into the collection
     * @return true if the collection is altered
     */
    public boolean add(E obj)
    {
        int originalSize = this.size();
        listIterator(originalSize).add(obj);
        return originalSize != this.size();
    }
 
    /**
     * Returns the location in the list of the first element that matches
     * the parameter object <tt>obj</tt> according to its equals method.
     * @param obj the object for which to search
     * @return the first index (from 0) where the object is found, or -1
     * if the object is not found in the list
     */
    public int indexOf(Object obj)
    {
        int index = 0;
        for (E element : this)
        {
            if (Utilities.nullSafeEquals(element, obj))
            {
                return index;
            }
            ++index;
        }
        return BAD_POSITION;
    }
    
    /**
     * Returns the location in the list of the last element that matches
     * the parameter object <tt>obj</tt> according to its equals method.
     * @param obj the object for which to search
     * @return the last index (from 0) where the object is found, or -1
     * if the object is not found in the list
     */
    public int lastIndexOf(Object obj)
    {
        int index = this.size() - 1;
        ListIterator<E> itr = this.listIterator(this.size());
        while (itr.hasPrevious())
        {
            if (Utilities.nullSafeEquals(itr.previous(), obj))
            {
                return index;
            }
            --index;
        }
        return BAD_POSITION;
    }
    
   /**
     * Returns the element of the list located at the specified index.
     * @param index the location of the element
     * @return the element at the index
     */ 
    public E get(int index)
    {
        checkValidIndex(index);
        return listIterator(index).next();
    }
    
    /**
     * Inserts the element into the list at the given location.
     * @param index the location at which to insert
     * @param obj the object to add to the collection
     */
    public void add(int index, E obj)
    {
        listIterator(index).add(obj);
    }
    
    /**
     * Inserts all the elements of <tt>coll</tt> into this collection at
     * the specified location.  If both this collection and the parameter
     * are the same collection, then the operational behavior is undefined
     * (i.e. bad things can happen).
     * @param index the location at which to insert
     * @param coll the collection from which to draw elements for addition.
     * @return true when this collection is modified as a result.
     */
    public boolean addAll(int index, Collection<? extends E> coll)
    {
        int size = this.size();
        ListIterator<E> itr = listIterator(index);
        for (E element : coll)
        {
            itr.add(element);
        }
        return size != this.size();
    }
    
    /**
     * Removes the element from the list at the specified index.
     * @param index the index of the object to be removed
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    public E remove(int index)
    {
        checkValidIndex(index);
        ListIterator<E> itr = listIterator(index);
        E result = itr.next();
        itr.remove();
        return result;
    }
    
    /**
     * Replaces the element at the specified index with the parameter
     * element.  Returns the element which was replaced.
     * @param index the index of the object to be replaced
     * @param obj the object to use as a replacement
     * @return the object originally at the location
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    public E set(int index, E obj)
    {
        checkValidIndex(index);
        ListIterator<E> itr = listIterator(index);
        E result = itr.next();
        itr.set(obj);
        return result;
    }
    
    /**
     * Used to check whether or not a given index falls within the
     * bounds of the list (i.e. between 0 inclusive and size() inclusive)
     */
    private void checkValidIndex(int index)
    {
        if (index < 0 || index >= this.size())
        {
            throw new IndexOutOfBoundsException(
                "Index " + index + " should be in [0..." + (size() - 1) + "]");
        }
    }

}
