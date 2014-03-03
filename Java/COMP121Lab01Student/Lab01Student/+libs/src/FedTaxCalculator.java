
/**
 * Calculate federal taxes based on the 2005 schedule available
 * at http://www.irs.gov/pub/irs-pdf/i1040tt.pdf.
 * 
 * @author Todd A. Whittaker
 * @version 2006-01
 */
public class FedTaxCalculator implements TaxCalculator
{
    /**
     * The annual dollar amount cutoffs for tax brackets.
     */
    static final Money [] CUTOFFS = {
        new Dollar(0),
        new Dollar(265000),
        new Dollar(980000),
        new Dollar(3150000),
        new Dollar(6975000),
        new Dollar(15195000),
        new Dollar(32825000)
    };

    /**
     * The corresponding percentages for tax brackets.
     */
    static final double [] TAX_RATES = {
        0.0,
        0.10,
        0.15,
        0.25,
        0.28,
        0.33,
        0.35
    };

    /**
     * Annual withholdings for the previous bracket.
     */
    static final Money [] ANN_WITHHOLD = {
        new Dollar(0),
        new Dollar(0),
        new Dollar(71500),
        new Dollar(397000),
        new Dollar(1353250),
        new Dollar(3654850),
        new Dollar(9472750)
    };

    /**
     * Calculate and return the amount of tax to pay on the net amount of
     * money earned in this pay cycle.
     * @param pay the gross earnings in this pay period
     * @param allowances the number of dependents
     * @param periods the number of pay periods per year
     * @return the amount of tax owed on the gross pay
     */
    public Money calculateTaxes(Money pay, int allowances, int periods)
    {
        Money yearIncome = pay.mul(periods).sub(
            new Dollar(320000).mul(allowances));
        
        if (yearIncome.compareTo(new Dollar(0)) < 0)
        {
            return new Dollar();
        }
        
        int bracket;
        for (bracket = 0; bracket < CUTOFFS.length; ++bracket)
        {
            if (yearIncome.compareTo(CUTOFFS[bracket]) < 0)
            {
                break;
            }
        }
        Money withhold = yearIncome.sub(CUTOFFS[bracket - 1]).mul(
            TAX_RATES[bracket - 1]).add(
            ANN_WITHHOLD[bracket - 1]).div(periods);
        return withhold;
    }
}
