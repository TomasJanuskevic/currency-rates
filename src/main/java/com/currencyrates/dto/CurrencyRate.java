package com.currencyrates.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currencyRateId;
    @JacksonXmlProperty(localName = "Ccy")
    private String currencyCode;
    @JacksonXmlProperty(localName = "Amt")
    private double rate;

    private String currencyName;

    @JsonBackReference(value = "ccyAmt")
    @ManyToOne
    @JoinColumn(name = "fxRate_id", nullable = false)
    private FxRate fxRate;
}
