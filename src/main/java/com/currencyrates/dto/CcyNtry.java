package com.currencyrates.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@ToString
@Getter
@Setter
public class CcyNtry {

    @JacksonXmlProperty(localName = "Ccy")
    private String ccy;
    @JacksonXmlProperty(localName = "CcyNm")
    private String ccyNm;
    @JacksonXmlProperty(localName = "CcyNbr")
    private String ccyNbr;
    @JacksonXmlProperty(localName = "CcyMnrUnts")
    private String ccyMnrUnts;

}
