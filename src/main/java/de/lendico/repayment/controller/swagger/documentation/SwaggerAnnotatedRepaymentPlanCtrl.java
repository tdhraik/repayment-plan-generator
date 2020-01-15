package de.lendico.repayment.controller.swagger.documentation;

import de.lendico.repayment.controller.view.request.AnnuityRepaymentRequest;
import de.lendico.repayment.controller.view.response.AnnuityRepaymentPlanResponse;
import io.swagger.annotations.*;

import java.util.List;

/**
 * Idea behind introducing this interface is not to corrupt the controller methods
 * with swagger documentation and hence keep the controller more readable.
 * All the swagger annotation are documented here.
 */
public interface SwaggerAnnotatedRepaymentPlanCtrl {


    @ApiOperation(value = "Generate repayment plan for annuity loan",
            responseContainer = "List", response = AnnuityRepaymentPlanResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    List<AnnuityRepaymentPlanResponse> getAnnuityRepaymentPlan(
            @ApiParam AnnuityRepaymentRequest annuityRepaymentRequest);
}
