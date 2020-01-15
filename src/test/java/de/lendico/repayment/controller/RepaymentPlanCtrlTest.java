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
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

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
        // arrange
        AnnuityRepaymentRequest request = new AnnuityRepaymentRequest(2, 5.0, BigDecimal.ZERO,
                ZonedDateTime.of(2018, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));

        // act and assert
        mvc.perform(post("/generate-plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.loanAmount", is("Total principal amount can not be less than 1.0")));
    }

    @Test
    public void whenGetRepaymentPlanForAnnuityLoanIsCalled_thenReturnRepaymentPlan() throws Exception {
        // arrange
        AnnuityRepaymentRequest request = new AnnuityRepaymentRequest(2, 5.0, new BigDecimal(10000),
                ZonedDateTime.of(2018, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")));

        // act and assert
        mvc.perform(post("/generate-plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
