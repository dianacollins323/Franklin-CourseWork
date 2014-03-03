
/**
 * Write a description of class Student here.
 * 
 * @author Diana Collins    
 * @version 1.0
 */
public class Student extends Person
{
    private String major;

    public Student(String name, int year, String maj)
    {
        super(name, year);
        this.setMajor(maj);
    }
    
    public String getMajor()
    {
        return this.major;
    }
    
    public void setMajor(String maj)
    {
        this.major = maj;
    }
    
    public String toString()
    {
        return super.toString() + ", Major: " + this.major;
    }
}
