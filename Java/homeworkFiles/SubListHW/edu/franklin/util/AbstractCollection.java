package edu.franklin.util;

/**
 * The base class for the collections hierarchy.  A collection is a group
 * of objects kept in a container.  Typical operations are to add, remove,
 * and iterate over the elements.  This class provides implementations for
 * all operations except <tt>add</tt>, <tt>size</tt>, and <tt>iterator</tt>.
 * Many of these operations can (and should) be overridden in derived
 * classes to increase the efficiency of the operation.  In particular,
 * <tt>clear</tt>, <tt>remove</tt>, <tt>addAll</tt>, and <tt>removeAll</tt>
 * are good candidates for advanced collections.
 * 
 * @param <E> the type of data to hold in the collection
 * 
 * @author Todd A. Whittaker
 * @version 2005-09
 */
public abstract class AbstractCollection <E> implements Collection<E>
{
    private boolean toStringLocked = false;
    private boolean hashCodeLocked = false;
    private boolean equalsLocked = false;
    
    /**
     * Searches the collection to determine if the collection contains
     * an element that matches the specified object.  If the specified
     * object is null, it will look for a null in the collection.  Otherwise
     * it will use the <tt>equals</tt> method of the given object to 
     * determine equality.  That is, <tt>o == null ? element == null : 
     * o.equals(element)</tt>.
     * @param obj the object to match
     * @return true if the collection contains a matching element
     */
    public boolean contains(Object obj)
    {
        boolean foundOne = false;
        
        // loop until we run off the end or find an element
        for (Iterator<E> itr = this.iterator(); itr.hasNext() && !foundOne; )
        {
            Object element = itr.next();
            
            // null safe equals comparison
            foundOne = Utilities.nullSafeEquals(obj, element);
        }
        return foundOne;
    }

    /**
     * Searches this collection to determine if every element of <tt>coll</tt>
     * exists in this collection.  Comparison is based on the same algorithm
     * as for <tt>contains</tt>.
     * @param coll the collection to be checked for containment in this on
     * @return true if all elements of <tt>coll</tt> also exist in this.
     */
    public boolean containsAll(Collection<? extends E> coll)
    {
        // relatively inefficient implementation
        for (E element : coll)
        {
            if (!(this.contains(element)))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Inserts all the elements of <tt>coll</tt> into this collection.  If
     * both this collection and the parameter are the same collection, then
     * the operational behavior is undefined (i.e. bad things can happen).
     * @param coll the collection from which to draw elements for addition.
     * @return true when this collection is modified as a result.
     */
    public boolean addAll(Collection<? extends E> coll)
    {
        boolean changed = false;
        for (E element : coll)
        {
            changed = this.add(element) || changed;
        }
        return changed;
    }
    
    /**
     * Returns true if the collection contains no elements.
     * @return true if the collection is empty.
     */
    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    /**
     * Removes the first element of this collection matching the parameter
     * object <tt>obj</tt>.  If the colleciton is altered as a result of
     * the operation, <tt>remove</tt> returns <tt>true</tt>.
     * @param obj the object to match
     * @return true if the collection is altered, false otherwise.
     */
    public boolean remove(Object obj)
    {
        for (Iterator <E> itr = this.iterator(); itr.hasNext(); )
        {
            if (Utilities.nullSafeEquals(obj, itr.next()))
            {
                itr.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all elements in this collection that match those in the
     * parameter collection <tt>coll</tt>.  When the operation completes,
     * the two collections will be disjoint.
     * @param coll the collection of elements to be removed from this one.
     * @return true if this collection is altered as a result of the call.
     */
    public boolean removeAll(Collection<?> coll)
    {
        boolean modified = false;
        for (Iterator<E> itr = this.iterator(); itr.hasNext(); )
        {
            if (coll.contains(itr.next()))
            {
                itr.remove();
                modified = true;
            }
        }
        return modified;
    }
    
    /**
     * Retains all the elements in this collection that match those in
     * the parameter collection <tt>coll</tt>.  That is, it will remove
     * all elements in this collection that have no match in <tt>coll</tt>.
     * @parma coll the collection of elements to be matched against
     * @return true if this collection is altered as a result of the call.
     */
    public boolean retainAll(Collection<?> coll)
    {
        boolean modified = false;
        for (Iterator<E> itr = this.iterator(); itr.hasNext(); )
        {
            if (!coll.contains(itr.next()))
            {
                itr.remove();
                modified = true;
            }
        }
        return modified;
    }
    
    /**
     * Removes all elements from the collection.  The collection will
     * be empty after calling this method.  This particular implementation
     * is slow, as it uses the iterator to remove elements one at a time
     * (for array-based collections, it is likely O(n^2).  This should
     * be overridden in the derived class.
     */
    public void clear()
    {
        for (Iterator<E> itr = this.iterator(); itr.hasNext(); )
        {
            itr.next();
            itr.remove();
        }
    }
    
    /**
     * Compares the parameter object <tt>obj</tt> against this collection
     * for equality.  Care should be taken to ensure symmetry, transitivity,
     * and reflexivity properties of equality.  That is: if
     * <tt>a.equals(b)</tt> is true then <tt>b.equals(a)</tt> should also be
     * true (symmetry); if <tt>a.equals(b)</tt> and <tt>b.equals(c)</tt> then
     * <tt>a.equals(c)</tt> should be true (transitivity); and also
     * <tt>a.equals(a)</tt> should also be true.
     * 
     * The contract between <tt>equals</tt> and <tt>hashCode</tt> should
     * also be kept.  That is, if <tt>a.equals(b)</tt> is true then
     * <tt>a.hashCode() == b.hashCode()</tt> should also be true.  The
     * reverse is not true (equal hash codes do not imply object equality).
     * 
     * @param obj the object against which to compare this collection.
     * @return true if the object is equal to this collection.
     */
    public boolean equals(Object obj)
    {
        // trivial equality if references are identical
        if (obj == this)
        {
            return true;
        }
        
        // can't compare against a non-collection
        if (!(obj instanceof Collection))
        {
            return false;
        }
        
        // another easy check: not the same if sizes are different
        Collection coll = (Collection) obj;
        if (this.size() != coll.size())
        {
            return false;
        }
        
        // if we're in the midst of a recursive call, return true.
        if (equalsLocked)
        {
            return true;
        }
        
        // lock to prevent infinite recursion on collections
        // that contain themselves (or mutually recursive collections).
        equalsLocked = true;
        
        boolean result = true;
        Iterator<E> itr1 = this.iterator();
        Iterator itr2 = coll.iterator();
        
        // do an element-by-element comparison
        while (itr1.hasNext() && result)
        {
            E element1 = itr1.next();
            Object element2 = itr2.next();
            
            if (!(Utilities.nullSafeEquals(element1, element2)))
            {
                result = false;
            }
        }
        equalsLocked = false;
        return result;
    }
    
    /**
     * Returns the hash code value for this collection.  This implementation
     * is typical in that it aggregates the hash codes of the individual
     * elements of the collection.
     * @return the hash code of the collection.
     */
    public int hashCode()
    {
        // note -- toArray, hashCode, and toString all "aggregate" a result
        // based on iteration through a collection.  This could make for
        // a good Strategy pattern implementation of Aggregator.

        // if we're in the midst of a recursive call, return trivial code.
        if (hashCodeLocked)
        {
            return 0;
        }
        
        int hashCode = 1;

        // lock to prevent infinite recursion on collections
        // that contain themselves (or mutually recursive collections).
        hashCodeLocked = true;

        // aggregate element hash codes by "shifting" by prime and
        // adding in the new hash code.
        for (E element : this)
        {
            hashCode = 31 * hashCode +
                (element == null ? 0 : element.hashCode());
        }
        hashCodeLocked = false;

        return hashCode;
    }

    /**
     * Returns a string representation of the collection (with elements
     * in a comma separated list).  Collections that are recursive
     * or mutually recursive are prevented from an infinite recursion
     * and the second and subsequent instances of their data are returned
     * as the string &quot;[...]&quot;.
     * @return a string representation of the collection
     */
    public String toString()
    {
        // note -- toArray, hashCode, and toString all "aggregate" a result
        // based on iteration through a collection.  This could make for
        // a good Strategy pattern implementation of Aggregator.

        // if we're in the midst of a recursive call, return trivial string
        if (toStringLocked)
        {
            return "[...]";
        }

        // lock to prevent infinite recursion on collections
        // that contain themselves (or mutually recursive collections).
        toStringLocked = true;
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");

        Iterator<E> itr = this.iterator();
        boolean hasNext = itr.hasNext();
        
        // aggregate string by iteration and concatenation.
        while (hasNext)
        {
            buffer.append(String.valueOf(itr.next()));
            hasNext = itr.hasNext();
            if (hasNext)
            {
                buffer.append(", ");
            }
        }
        buffer.append("]");
        
        toStringLocked = false;
        return buffer.toString();
    }
    
    /**
     * Creates and returns an array containing the data from this collection.
     * If this collection preserves order, then the order in the array
     * will be the same as the order of the collection.  The array returned
     * will always be safe to be modified (i.e. it will be different from
     * the underlying collection's array if the collection is array-backed).
     * @return an array containing references to the collections elements.
     */
    public Object[] toArray()
    {
        // note -- toArray, hashCode, and toString all "aggregate" a result
        // based on iteration through a collection.  This could make for
        // a good Strategy pattern implementation of Aggregator.
        
        Object [] result = new Object[this.size()];
        
        int i = 0;
        for (E element : this)
        {
            result[i] = element;
            ++i;
        }
        return result;
    }
    
    /**
     * Returns an array containing the data from this collection.  If the
     * parameter array is large enough to hold the collection, then it is
     * filled and returned.  Otherwise, a new array of the same type is
     * allocated, filled, and returned.  If this collection preserves order
     * then ten the order in the array will be the same as the order of the
     * collection. The array returned will always be safe to be modified
     * (i.e. it will be different from the underlying collection's array
     * if the collection is array-backed).
     * @return an array containing references to the collections elements.
     */
    public <T> T[] toArray(T[] arr)
    {
        Object [] result = null;
        
        if (arr == null || arr.length < this.size())
        {
            arr = Utilities.newArray(arr, this.size());
        }
        
        // some trickery here.  An array of T is always also an array
        // of Object, so we can fill "result", but return arr and still
        // match types.
        result = arr;

        int i = 0;
        for (E element : this)
        {
            result[i] = element;
            ++i;
        }
        if (i < result.length)
        {
            result[i] = null;
        }
        return arr;
    }
}
