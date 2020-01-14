package de.lendico.repayment.controller.view.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnnuityRepaymentRequest {

    @NotNull
    @Range(min = 1, max = 120, message = "Number of instalments should be between 1 and 120")
    private Integer numberOfInstalments;

    @NotNull
    private Double nominalInterestRate;

    @NotNull
    @Range(min = 1L, message = "Total principal amount can not be less than 1.0")
    private BigDecimal totalPrincipalAmount;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "YYYY-MM-dd")
    private LocalDate payoutDate;
}
