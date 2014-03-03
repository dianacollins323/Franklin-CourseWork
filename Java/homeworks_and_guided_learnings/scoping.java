
/**
 * Write a description of class scoping here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class scoping
{
    public static void main(String args[])
    {
        System.out.println(scoping.foo());
        System.out.println(scoping.boo());
    }
    
    private static int a = 2;
    
    public static int foo()
    {
        int b = a + 2;
        return b;
    }
    
    public static int boo()
    {
        int a = 3;
        return foo();
    }
}
