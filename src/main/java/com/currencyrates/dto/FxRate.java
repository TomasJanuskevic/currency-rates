package com.currencyrates.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class FxRate {
    @JacksonXmlProperty(localName = "Tp")
    private String tp;
    @JacksonXmlProperty(localName = "Dt")
    private String dt;
    @JacksonXmlProperty(localName = "CcyAmt")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CcyAmt> ccyAmt;
}
