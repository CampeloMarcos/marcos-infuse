package br.com.campelo.marcos.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDTO implements Serializable {

    @XmlElement
    @JsonProperty(value = "control-number")
    @NotNull(message = "msg.error.field.required")
    private BigInteger controlNumber;
    @XmlElement
    @JsonProperty(value = "date-register")
    private LocalDate dateRegister;
    @XmlElement
    @NotBlank(message = "msg.error.field.required")    
    private String name;
    @XmlElement
    @NotNull(message = "msg.error.field.required")
    private BigDecimal price;
    private Integer amount;

    public LocalDate getDateRegister() {
        if(dateRegister == null) {
            dateRegister = LocalDate.now();
        }
        return this.dateRegister;
    }

    public Integer getAmount() {
        if(amount == null || amount <= 0) {
            amount = 1;
        }
        return this.amount;
    }
}
