
/**
 * Write a description of class testVehicleCar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class testVehicleCar
{
    public static void main(String[] args)
    {
        Vehicle myCar = new Car("car");
        System.out.println("The " + myCar.type + " has been started");
        System.out.println(myCar.start());
    }
}
