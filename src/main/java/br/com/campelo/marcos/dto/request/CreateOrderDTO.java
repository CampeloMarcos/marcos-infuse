package br.com.campelo.marcos.dto.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "create-order")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateOrderDTO implements Serializable {

    @Valid
    @XmlElement
    @NotNull(message = "msg.error.field.required")
    @Size(min = 1, max = 10, message = "msg.error.field.size")
    private List<OrderDTO> orders;
    @XmlElement
    @JsonProperty(value = "client-code")
    @NotNull(message = "msg.error.field.required")
    private Long clientCode;
}
