import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This class creates and store polynomials
 * 
 * @author Diana Collins 
 * @version 1.0
 */
public class Polynomial
{
    LinkedList<Term> terms = null;
    
    public Polynomial()
    {
        terms = new LinkedList<Term>();
    }
    
    public void addTerm(Term term)
    {
        terms.add(term);
    }
    
    /**
     * adds two polynomials
     */
    public Polynomial addPolys(Polynomial poly)
    {
        ListIterator itr1 = this.getTerms().listIterator();
        ListIterator itr2 = poly.getTerms().listIterator();
        Polynomial newTerms = new Polynomial();
        Term thisTerm = (Term) itr1.next();
        Term otherTerm = (Term) itr2.next();
        int count = 0;
        
        while (count <= this.getTerms().size() && count <= poly.getTerms().size())
        {
            if (thisTerm.compareTo(otherTerm) > 0)
            {
                newTerms.addTerm(thisTerm);
                if (itr1.hasNext())
                {
                    thisTerm = (Term) itr1.next();
                }
            }
            else if (thisTerm.compareTo(otherTerm) < 0)
            {
                newTerms.addTerm(otherTerm);
                if(itr2.hasNext())
                {
                    otherTerm = (Term) itr2.next();
                }
            }
            else
            {
                int newCo = thisTerm.addCoefficients(otherTerm.getCoefficient());
                Term term = new Term(newCo, thisTerm.getVariable(), thisTerm.getExponent());
                newTerms.addTerm(term);
                if (itr1.hasNext())
                {
                    thisTerm = (Term) itr1.next();
                }
                if (itr2.hasNext())
                {
                    otherTerm = (Term) itr2.next();
                }
            }
            count++;
        }
        
        return newTerms;
    }
    
    /**
     * returns terms LinkedList
     */
    public LinkedList<Term> getTerms()
    {
        return terms;
    }
    
    /**
     * override toString method to print polynomial
     */
    public String toString()
    {
        ListIterator itr = terms.listIterator();
        String poly = null;
        
        while (itr.hasNext())
        {
            if (!itr.hasPrevious())
            {
                poly = itr.next().toString();
            }
            else
            {
                poly = poly + itr.next().toString();    
            }
        }
        
        return poly;
    }
}
