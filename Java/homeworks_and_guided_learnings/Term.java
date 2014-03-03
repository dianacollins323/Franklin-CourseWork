
/**
 * This class creates terms which will
 * be used in the polnomial class.
 * 
 * @author Diana Collins 
 * @version 1.0
 */
public class Term implements Comparable
{
    int coefficient = 0;
    String variable = null;
    Integer exponent = 1;
    
    public Term(int co, String var, Integer ex)
    {
        setCoefficient(co);
        setVariable(var);
        setExponent(ex);
    }
    
    /**
     * adds coefficients
     */
    public int addCoefficients(int co)
    {
        int newCoefficient = this.getCoefficient() + co;
        return newCoefficient;
    }
    
    /**
     * compares variables and exponents
     */
    public int compareTo(Object term)
    {
        Term comTerm = (Term) term;
        return this.getExponent().compareTo(comTerm.getExponent());
    }
    
    /**
     * override toString method to print polynomial terms
     */
    public String toString()
    {
        if (coefficient >= 0 && exponent <= 1)
        {
            return "+" + coefficient + variable;
        }
        else if (coefficient >= 0)
        {
            return "+" + coefficient + variable + exponent;
        }
        else if (exponent <= 1)
        {
             return coefficient + variable;
        }
        else
        {
            return coefficient + variable + exponent;
        }
    }
    
    /**
     * returns coefficient
     */
    public int getCoefficient()
    {
        return coefficient;
    }
    
    /**
     * sets coefficient value
     */
    public void setCoefficient(int co)
    {
        coefficient = co;
    }
    
    /**
     * returns variable
     */
    public String getVariable()
    {
        return variable;
    }
    
    /**
     * sets variable value
     */
    public void setVariable(String var)
    {
        variable = var;
    }
    
    /**
     * returns exponent
     */
    public Integer getExponent()
    {
        return exponent;
    }
    
    /**
     * sets exponent value
     */
    public void setExponent(int ex)
    {
        exponent = ex;
    }
}
