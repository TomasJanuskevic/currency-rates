package com.currencyrates.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {
    private String currency;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date format yyyy-mm-dd")
    private String dateFrom;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date format yyyy-mm-dd")
    private String dateTo;
    @Min(value = 1, message = "Amount can't be 0 or negative")
    @Max(value = 1_000_000_000, message = "Max amount can be 1000000000")
    @NotNull(message = "Please enter a valid amount")
    private Integer currencyValue = 1;
    private double currencyRate;
}
