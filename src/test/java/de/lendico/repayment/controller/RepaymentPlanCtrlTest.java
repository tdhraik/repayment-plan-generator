package de.lendico.repayment.controller;

import de.lendico.repayment.controller.view.request.AnnuityRepaymentRequest;
import de.lendico.repayment.helper.TestUtil;
import de.lendico.repayment.service.AnnuityRepaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RepaymentPlanCtrlTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AnnuityRepaymentService annuityService;

    @Test
    public void whenGetRepaymentPlanForAnnuityLoanIsCalled_WithLoanAmountAsZero_thenReturnValidationException() throws Exception {
        AnnuityRepaymentRequest request = new AnnuityRepaymentRequest(2, 5.0, BigDecimal.ZERO, ZonedDateTime.now());

        mvc.perform(post("/generate-plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
