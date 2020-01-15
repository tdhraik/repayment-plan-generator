package de.lendico.repayment.util;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;

@Service
public class RepaymentAnnuityHelper {

    /**
     * application wide default scaling to be added to the money amount.
     * @param value - value which needs to be scaled
     * @return scaled value
     */
    public static BigDecimal addScale(BigDecimal value) {
        return value.setScale(2, RoundingMode.CEILING);
    }

    /**
     * calculate annuity amount
     * @param principalAmount - amount on which the annuity amount is calculated
     * @param interestRate - nominal interest rate
     * @param numberOfInstalments - number of annuity instalments (in months)
     * @return annuity amount
     */
    public static BigDecimal calculateAnnuity(BigDecimal principalAmount, Double interestRate, Integer numberOfInstalments) {
        BigDecimal rate = new BigDecimal(interestRate/100).divide(new BigDecimal(12), RoundingMode.HALF_UP);
        BigDecimal numerator = principalAmount.multiply(rate);
        BigDecimal addOneToRate = ONE.add(rate);
        BigDecimal denominator = ONE.subtract(new BigDecimal(Math.pow(addOneToRate.doubleValue(), -numberOfInstalments)));
        BigDecimal finalValue = numerator.divide(denominator, RoundingMode.HALF_UP);
        return addScale(finalValue);
    }

    /**
     * calculate interest incurred at each instalment
     * @param principalAmount - amount on which interest is calculated
     * @param interestRate - nominal interest rate
     * @return interest
     */
    public static BigDecimal calculateInterest(BigDecimal principalAmount, Double interestRate) {
        BigDecimal rate = new BigDecimal(interestRate/100).divide(new BigDecimal(12), RoundingMode.HALF_DOWN);
        BigDecimal numerator = rate.multiply(new BigDecimal(30)).multiply(principalAmount);
        BigDecimal denominator = new BigDecimal(30);
        BigDecimal finalValue = numerator.divide(denominator, RoundingMode.HALF_DOWN);
        return addScale(finalValue);
    }

}
