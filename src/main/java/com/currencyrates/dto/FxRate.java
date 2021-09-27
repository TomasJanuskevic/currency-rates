package com.currencyrates.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
public class FxRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fxRateId;

    @JacksonXmlProperty(localName = "Tp")
    private String tp;

    @JacksonXmlProperty(localName = "Dt")
    private String dt;

    @JacksonXmlProperty(localName = "CcyAmt")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonManagedReference(value = "ccyAmt")
    @OneToMany(mappedBy = "fxRate", cascade = CascadeType.ALL)
    private List<currencyRate> currencyRate;
}
