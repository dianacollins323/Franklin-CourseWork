
/**
 * Write a description of class Person here.
 * 
 * @author Diana Collins 
 * @version 1.0
 */
public class Person
{
    private String fullName;
    private int birthYear;

    public Person(String name, int year)
    {
        this.setFullName(name);
        this.setBirthYear(year);
    }
    
    public String getFullName()
    {
        return this.fullName;
    }
    
    public void setFullName(String name)
    {
        this.fullName = name;
    }
    
    public int getBirthYear()
    {
        return this.birthYear;
    }
    
    public void setBirthYear(int year)
    {
        this.birthYear = year;
    }
    
    public String toString()
    {
        return "Name: " + this.fullName + ", Birth Year: " + this.birthYear;
    }
}
