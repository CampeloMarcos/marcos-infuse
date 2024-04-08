package br.com.campelo.marcos.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "order")
public class ListOrderDTO implements Serializable {

    @XmlElement
    @JsonProperty(value = "control-number")
    private BigInteger controlNumber;
    @XmlElement
    @JsonProperty(value = "date-register")
    private LocalDate dateRegister;
    @XmlElement
    private String name;
    @XmlElement
    private BigDecimal price;
    @XmlElement
    private Integer amount;
    @XmlElement
    @JsonProperty(value = "total-order-price")
    private BigDecimal totalOrderPrice;
    @XmlElement
    @JsonProperty(value = "client-code")
    private Long clientCode;
    @XmlElement
    @JsonProperty(value = "client-name")
    private String clientName;
    
}
