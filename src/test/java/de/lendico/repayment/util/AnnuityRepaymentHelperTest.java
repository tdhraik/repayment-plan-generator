package de.lendico.repayment.util;

import mockit.Deencapsulation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AnnuityRepaymentHelperTest {

    @Test
    public void testCalculateAnnuityAmount() {
        // arrange
        BigDecimal principalAmount = new BigDecimal(5000);
        Double nominalRate = 5.0;
        Integer numberOfInstalments = 24;

        // act
        BigDecimal annuityAmount = Deencapsulation.invoke(AnnuityRepaymentHelper.class,
                "calculateAnnuity", principalAmount, nominalRate, numberOfInstalments);

        // assert
        assertThat(annuityAmount).isEqualTo("219.36");
    }

    @Test(expected = ArithmeticException.class)
    public void testCalculateAnnuityAmountWithZeroInterestRate() {
        // arrange
        BigDecimal principalAmount = new BigDecimal(5000);
        Double nominalRate = 0.0;
        Integer numberOfInstalments = 24;

        // act
        BigDecimal annuityAmount = Deencapsulation.invoke(AnnuityRepaymentHelper.class,
                "calculateAnnuity", principalAmount, nominalRate, numberOfInstalments);

    }

    @Test
    public void testCalculateInterest() {
        // arrange
        BigDecimal principalAmount = new BigDecimal(5000);
        Double nominalRate = 5.0;

        // act
        BigDecimal interestAmount = Deencapsulation.invoke(AnnuityRepaymentHelper.class,
                "calculateInterest", principalAmount, nominalRate);

        // assert
        assertThat(interestAmount).isEqualTo("20.84");
    }
}
