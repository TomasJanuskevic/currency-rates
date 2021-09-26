package com.currencyrates.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter

@Entity
@Table(name = "Currency_List")
public class CcyNtry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currencyId;

    @JacksonXmlProperty(localName = "Ccy")
    private String ccy;

    @JacksonXmlProperty(localName = "CcyNm")
    private String ccyNm;

    @JacksonXmlProperty(localName = "CcyNbr")
    private String ccyNbr;

    @JacksonXmlProperty(localName = "CcyMnrUnts")
    private String ccyMnrUnts;

}
