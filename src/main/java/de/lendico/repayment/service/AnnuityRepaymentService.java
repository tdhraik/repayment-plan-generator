package de.lendico.repayment.service;

import de.lendico.repayment.controller.view.request.AnnuityRepaymentRequest;
import de.lendico.repayment.controller.view.response.AnnuityRepaymentPlanResponse;
import org.springframework.stereotype.Service;

import static de.lendico.repayment.controller.view.response.AnnuityRepaymentPlanResponse.AnnuityRepaymentPlanResponseBuilder;

@Service
public class AnnuityRepaymentService {

    public AnnuityRepaymentPlanResponse getAnnuityRepaymentPlan(AnnuityRepaymentRequest annuityReq) {
        return AnnuityRepaymentPlanResponseBuilder.anAnnuityRepaymentPlanResponse()
                .build();
    }

}
