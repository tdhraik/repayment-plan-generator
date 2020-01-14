package de.lendico.repayment.controller.swagger.documentation;

import de.lendico.repayment.controller.view.request.AnnuityRepaymentRequest;
import de.lendico.repayment.controller.view.response.AnnuityRepaymentPlanResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Idea behind introducing this interface is not to corrupt the controller methods
 * with swagger documentation and hence keep the controller more readable.
 * All the swagger annotation are documented here.
 */
public interface SwaggerAnnotatedRepaymentPlanCtrl {

    @ApiOperation(value = "Generate repayment plan for annuity loan",
        response = AnnuityRepaymentPlanResponse.class)
    AnnuityRepaymentPlanResponse getAnnuityRepaymentPlan(
            @ApiParam AnnuityRepaymentRequest annuityRepaymentRequest);
}
