
/**
 * This class represents a television to be rented.
 *
 * @author Franklin University
 * @version Winter 2013
 */
public class Television extends AbstractItem
{
    private int tvSize;
    private String tvType;

    /**
     * Constructor for objects of class Television.
     */
    public Television()
    {
        //blah
    }
    
    /**
     * Return the size of the television.
     * @return the size.
     */
    public int getSize()
    {
        return tvSize;
    }

    /**
     * Change the size of the television to the given
     * parameter.
     * @param screenSize the new size.
     */
    public void setSize(int screenSize)
    {
        if (screenSize < 5)
        {
            throw new IllegalArgumentException("size cannot be less than 5");
        }
        else if (screenSize > 60)
        {
            throw new IllegalArgumentException("size cannot be more than 60");
        }
        else if (tvType == null)
        {
            tvSize = screenSize;
        }
        else if (tvType.equalsIgnoreCase("LCD"))
        {
            if (screenSize >= 5 && screenSize <= 50)
            {
                tvSize = screenSize;
            }
            else
            {
                throw new IllegalArgumentException("invalid screen size");
            }
        }
        else if (tvType.equalsIgnoreCase("plasma"))
        {
            if (screenSize >= 37 && screenSize <= 60)
            {
                tvSize = screenSize;
            }
            else
            {
                throw new IllegalArgumentException("invalid screen size");
            }
        }
    }

    /**
     * Return the type of the television.
     * @return the type.
     */
    public String getType()
    {
        return tvType;
    }

    /**
     * Change the type of the television to the given
     * parameter.
     * @param screenType the new type.
     */
    public void setType(String screenType)
    {
        if (screenType == null)
        {
            throw new IllegalArgumentException("type cannot be null");
        }
        else if (screenType.equals(""))
        {
            throw new IllegalArgumentException("type cannot be empty string");
        }
        else if (tvSize == 0)
        {
            if (screenType.equalsIgnoreCase("LCD") || 
                screenType.equalsIgnoreCase("plasma"))
            {
                tvType = screenType;
            }
            else
            {
                throw new IllegalArgumentException("type not valid");
            }
        }
        else if (tvSize >= 37 && tvSize <= 50)
        {
            if (screenType.equalsIgnoreCase("LCD") || 
                screenType.equalsIgnoreCase("plasma"))
            {
                tvType = screenType;
            }
            else
            {
                throw new IllegalArgumentException("type not valid");
            }
        }
        else if (screenType.equalsIgnoreCase("LCD"))
        {
            if (tvSize >= 5 && tvSize <= 50)
            {
                tvType = screenType;
            }
            else
            {
                throw new IllegalArgumentException("type not valid" +
                    " for this size tv");    
            }
        }
        else if (screenType.equalsIgnoreCase("plasma"))
        {
            if (tvSize >= 37 && tvSize <= 60)
            {
                tvType = screenType;
            }
            else
            {
                throw new IllegalArgumentException("type not valid" +
                    " for this size tv"); 
            }
        }
        else
        {
            throw new IllegalArgumentException("type not valid");
        }
    }

    /**
     * overriding equals method to account for 
     * television size and type
     * @param o reference to item to match
     * @return true if item is exact match 
     * false otherwise
     */
    public boolean equals(Object o)
    {
        Television temp = (Television) o;
        boolean matchSize = false;
        boolean matchType = false;
        
        //compare tvSize
        if (tvSize == 0 && temp.getSize() == 0)
        {
            matchSize = true;
        }
        else if (temp.getSize() == 0)
        {
            matchSize = false;
        }
        else if (tvSize != temp.getSize())
        {
            matchSize = false;
        }
        else
        {
            matchSize =  true;
        }
        
        //compare tvType
        if (tvType == null && temp.getType() == null)
        {
            matchType = true;
        }
        else if (temp.getType() == null)
        {
            matchType = false;
        }
        else if (tvType == null)
        {
            matchType = false;
        }
        else if (!tvType.equals(temp.getType()))
        {
            matchType = false;
        }
        else
        {
            matchType = true;
        }
        
        //match items
        if (!super.equals(temp))
        {
            return false;
        }
        else if (!matchSize)
        {
            return false;
        }
        else if (!matchType)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
