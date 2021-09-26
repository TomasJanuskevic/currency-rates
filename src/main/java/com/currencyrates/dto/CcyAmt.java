package com.currencyrates.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CcyAmt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ccyAmtId;
    @JacksonXmlProperty(localName = "Ccy")
    private String ccy;
    @JacksonXmlProperty(localName = "Amt")
    private double amt;

    @JsonBackReference(value = "ccyAmt")
    @ManyToOne
    @JoinColumn(name = "fxRate_id", nullable = false)
    private FxRate fxRate;
}
