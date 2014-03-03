
/**
 * An object that encapsulates the calculation of taxes based on the
 * amount of money earned, the number of dependents and the number
 * of pay periods in the year.
 * 
 * @author Todd A. Whittaker
 * @version 2006-01
 */
public interface TaxCalculator
{
    /**
     * Calculate and return the amount of tax to pay on the net amount of
     * money earned in this pay cycle.
     * @param pay the gross earnings in this pay period
     * @param allowances the number of dependents
     * @param periods the number of pay periods per year
     * @return the amount of tax owed on the gross pay
     */
    Money calculateTaxes(Money pay, int allowances, int periods);
}
