package com.currencyrates.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {
    private String currency;
    private String dateFrom;
    private String dateTo;
}
