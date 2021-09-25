package com.currencyrates.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CcyAmt {
    @JacksonXmlProperty(localName = "Ccy")
    private String ccy;
    @JacksonXmlProperty(localName = "Amt")
    private double amt;
}
