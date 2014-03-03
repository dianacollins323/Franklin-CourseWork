
/**
 * Write a description of class Car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car extends Vehicle
{
    public String type = "vehicle";
    
    public Car (String type)
    {
        this.type = type;
    }
    
    public String start()
    {
        String start = "The " + type + " has been started";
        return start;
    }
}
