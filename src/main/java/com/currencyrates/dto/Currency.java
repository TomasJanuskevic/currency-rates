package com.currencyrates.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter

@Entity
@Table(name = "Currency_List")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currencyId;

    @JacksonXmlProperty(localName = "Ccy")
    private String currencyCode;

    @JacksonXmlProperty(localName = "CcyNm")
    private String currencyName;

    @JacksonXmlProperty(localName = "CcyNbr")
    private String currencyNumber;

    @JacksonXmlProperty(localName = "CcyMnrUnts")
    private String ccyMnrUnts;

}
