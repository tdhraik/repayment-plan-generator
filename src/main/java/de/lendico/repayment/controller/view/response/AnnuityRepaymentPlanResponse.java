package de.lendico.repayment.controller.view.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class AnnuityRepaymentPlanResponse {

    @ApiModelProperty(required = true)
    private BigDecimal borrowerPaymentAmount;

    @ApiModelProperty(required = true)
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Berlin")
    private LocalDateTime date;

    @ApiModelProperty(required = true)
    private BigDecimal initialOutstandingPrincipal;

    @ApiModelProperty(required = true)
    private BigDecimal interest;

    @ApiModelProperty(required = true)
    private BigDecimal principal;

    @ApiModelProperty(required = true)
    private BigDecimal remainingOutstandingPrincipal;

    public static final class AnnuityRepaymentPlanResponseBuilder {
        private BigDecimal borrowerPaymentAmount;
        private LocalDateTime date;
        private BigDecimal initialOutstandingPrincipal;
        private BigDecimal interest;
        private BigDecimal principal;
        private BigDecimal remainingOutstandingPrincipal;

        private AnnuityRepaymentPlanResponseBuilder() {
        }

        public static AnnuityRepaymentPlanResponseBuilder anAnnuityRepaymentPlanResponse() {
            return new AnnuityRepaymentPlanResponseBuilder();
        }

        public AnnuityRepaymentPlanResponseBuilder withBorrowerPaymentAmount(BigDecimal borrowerPaymentAmount) {
            this.borrowerPaymentAmount = borrowerPaymentAmount;
            return this;
        }

        public AnnuityRepaymentPlanResponseBuilder withDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public AnnuityRepaymentPlanResponseBuilder withInitialOutstandingPrincipal(BigDecimal initialOutstandingPrincipal) {
            this.initialOutstandingPrincipal = initialOutstandingPrincipal;
            return this;
        }

        public AnnuityRepaymentPlanResponseBuilder withInterest(BigDecimal interest) {
            this.interest = interest;
            return this;
        }

        public AnnuityRepaymentPlanResponseBuilder withPrincipal(BigDecimal principal) {
            this.principal = principal;
            return this;
        }

        public AnnuityRepaymentPlanResponseBuilder withRemainingOutstandingPrincipal(BigDecimal remainingOutstandingPrincipal) {
            this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
            return this;
        }

        public AnnuityRepaymentPlanResponse build() {
            return new AnnuityRepaymentPlanResponse(borrowerPaymentAmount, date, initialOutstandingPrincipal, interest, principal, remainingOutstandingPrincipal);
        }
    }
}
