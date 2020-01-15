package de.lendico.repayment.controller.view.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnnuityRepaymentRequest {

    @NotNull
    @Range(min = 1, max = 120, message = "Number of instalments should be between 1 and 120")
    private Integer duration;

    @NotNull
    private Double nominalRate;

    @NotNull
    @Range(min = 1L, message = "Total principal amount can not be less than 1.0")
    private BigDecimal loanAmount;

    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime startDate;
}
