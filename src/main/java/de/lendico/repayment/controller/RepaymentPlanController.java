package de.lendico.repayment.controller;

import de.lendico.repayment.controller.swagger.documentation.SwaggerAnnotatedRepaymentPlanCtrl;
import de.lendico.repayment.controller.view.request.AnnuityRepaymentRequest;
import de.lendico.repayment.controller.view.response.AnnuityRepaymentPlanResponse;
import de.lendico.repayment.service.AnnuityRepaymentService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Idea behind having a controller / resource layer is to map request, validate it, and return response.
 * Controllers should be light weight with almost no business logic.
 * Only validate request and forward it to relevant service layer / interactors ( in Domain Driven Design )
 */

@RestController
@RequestMapping("/generate-plan")
@Api(value = "/generate-plan", description = "Calculate repayment plan for various loans", tags = "repayment-plan-generator-service")
public class RepaymentPlanController implements SwaggerAnnotatedRepaymentPlanCtrl {

    private static final Logger log = LoggerFactory.getLogger(RepaymentPlanController.class);

    private AnnuityRepaymentService annuityService;

    public RepaymentPlanController(AnnuityRepaymentService annuityService) {
        this.annuityService = annuityService;
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AnnuityRepaymentPlanResponse> getAnnuityRepaymentPlan(
            @Valid @RequestBody AnnuityRepaymentRequest annuityRepaymentReq) {
        log.info("Get annuity repayment plan called with request - {}", annuityRepaymentReq);
        return annuityService.getAnnuityRepaymentPlan(annuityRepaymentReq);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleRequestValidation(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
