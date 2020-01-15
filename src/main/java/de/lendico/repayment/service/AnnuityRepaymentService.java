package de.lendico.repayment.service;

import de.lendico.repayment.controller.view.request.AnnuityRepaymentRequest;
import de.lendico.repayment.controller.view.response.AnnuityRepaymentPlanResponse;
import de.lendico.repayment.util.RepaymentAnnuityHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.lendico.repayment.controller.view.response.AnnuityRepaymentPlanResponse.AnnuityRepaymentPlanResponseBuilder;

@Service
public class AnnuityRepaymentService {

    public List<AnnuityRepaymentPlanResponse> getAnnuityRepaymentPlan(AnnuityRepaymentRequest annuityReq) {
        BigDecimal annuityAmount = RepaymentAnnuityHelper.calculateAnnuity(annuityReq.getLoanAmount(),
                annuityReq.getNominalRate(), annuityReq.getDuration());
        return IntStream
                .rangeClosed(1, annuityReq.getDuration())
                .sequential()
                .mapToObj(i -> {
                    if(i==annuityReq.getDuration()) {
                        return createLastInstalmentResponse(annuityReq); // handle final instalment
                    }
                    AnnuityRepaymentPlanResponse resp = createAnnuityRepaymentResponse(annuityReq, annuityAmount);
                    annuityReq.setLoanAmount(resp.getRemainingOutstandingPrincipal());
                    annuityReq.setStartDate(resp.getDate().plusMonths(1));
                    return resp;
                })
                .collect(Collectors.toList());
    }

    private AnnuityRepaymentPlanResponse createAnnuityRepaymentResponse(AnnuityRepaymentRequest request, BigDecimal annuityAmount) {
        BigDecimal interest = RepaymentAnnuityHelper.calculateInterest(request.getLoanAmount(), request.getNominalRate());
        BigDecimal principal = annuityAmount.subtract(interest);
        BigDecimal remainingOutPrincipal = request.getLoanAmount().subtract(principal);
        return AnnuityRepaymentPlanResponseBuilder.anAnnuityRepaymentPlanResponse()
                .withBorrowerPaymentAmount(annuityAmount)
                .withDate(request.getStartDate())
                .withInitialOutstandingPrincipal(request.getLoanAmount())
                .withInterest(interest)
                .withPrincipal(principal)
                .withRemainingOutstandingPrincipal(remainingOutPrincipal)
                .build();
    }

    private AnnuityRepaymentPlanResponse createLastInstalmentResponse(AnnuityRepaymentRequest request) {
        BigDecimal interest = RepaymentAnnuityHelper.calculateInterest(request.getLoanAmount(), request.getNominalRate());
        BigDecimal finalAnnuityAmount = request.getLoanAmount().add(interest);
        return AnnuityRepaymentPlanResponseBuilder.anAnnuityRepaymentPlanResponse()
                .withBorrowerPaymentAmount(finalAnnuityAmount)
                .withDate(request.getStartDate())
                .withInitialOutstandingPrincipal(request.getLoanAmount())
                .withInterest(interest)
                .withPrincipal(finalAnnuityAmount.subtract(interest))
                .withRemainingOutstandingPrincipal(BigDecimal.ZERO)
                .build();
    }


}
