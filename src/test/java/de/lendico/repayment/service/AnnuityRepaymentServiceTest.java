package de.lendico.repayment.service;

import de.lendico.repayment.controller.view.request.AnnuityRepaymentRequest;
import de.lendico.repayment.controller.view.response.AnnuityRepaymentPlanResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AnnuityRepaymentServiceTest {

    private AnnuityRepaymentService service;

    @Before
    public void setUp() {
        service = new AnnuityRepaymentService();
    }

    @Test
    public void testGetAnnuityRepaymentPlan() {
        AnnuityRepaymentRequest request = new AnnuityRepaymentRequest(
            24, 5.0, new BigDecimal(5000), ZonedDateTime.of(2018, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));
        List<AnnuityRepaymentPlanResponse> repaymentPlan = service.getAnnuityRepaymentPlan(request);
        assertThat(repaymentPlan.size()).isEqualTo(24);
        assertThat(repaymentPlan.get(0).getBorrowerPaymentAmount().toString()).isEqualTo("219.36");
        assertThat(repaymentPlan.get(0).getInterest().toString()).isEqualTo("20.84");
        assertThat(repaymentPlan.get(23).getRemainingOutstandingPrincipal().toString()).isEqualTo("0");
    }
}
