
/**
 * Write a description of class TestPeople here.
 * 
 * @author Diana Collins 
 * @version 1.0
 */
public class TestPeople
{
    public static void main(String[] args)
    {
        Person frances = new Person("Frances Collins", 2011);
        Student olivia = new Student("Olivia Collins", 2005, "general studies");
        Instructor mommy = new Instructor("Diana Collins", 1981, 20000);
        
        System.out.println(frances.toString());
        System.out.println(olivia.toString());
        System.out.println(mommy.toString());
    }
}
