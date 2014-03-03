
/**
 * This class creates two polynomials
 * and adds them together. It then
 * prints the two original polynomials
 * and the polynomial sum.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestAddPolynomials
{
    public static void main(String[] args)
    {
        //create two empty polynomials
        Polynomial poly1 = new Polynomial();
        Polynomial poly2 = new Polynomial();
        //Polynomial newPoly = new Polynomial();
        
        //create terms for poly1
        Term p1t1 = new Term(3, "x", 4);
        Term p1t2 = new Term(-2, "x", 2);
        Term p1t3 = new Term(7, "x", 1);
        Term p1t4 = new Term(-4, "", 1);
        //add terms to poly1
        poly1.addTerm(p1t1);
        poly1.addTerm(p1t2);
        poly1.addTerm(p1t3);
        poly1.addTerm(p1t4);
        
        //create terms for poly2
        Term p2t1 = new Term(-1, "x", 4);
        Term p2t2 = new Term(5, "x", 3);
        Term p2t3 = new Term(5, "x", 2);
        Term p2t4 = new Term(-2, "x", 1);
        Term p2t5 = new Term(9, "", 1);
        //add terms to poly2
        poly2.addTerm(p2t1);
        poly2.addTerm(p2t2);
        poly2.addTerm(p2t3);
        poly2.addTerm(p2t4);
        poly2.addTerm(p2t5);
        
        //add polynomials
        Polynomial newPoly = poly1.addPolys(poly2);
        
        //print original polynomials
        //and polynomial sum
        System.out.println(poly1.toString());
        System.out.println(poly2.toString());
        System.out.println(newPoly.toString());
    }
}
