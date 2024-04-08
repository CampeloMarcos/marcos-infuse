package br.com.campelo.marcos.dto.response;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import br.com.campelo.marcos.dto.response.abstracts.AbstractBasePageResponseBodyDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "orders")
public class ListOrderResponseDTO extends AbstractBasePageResponseBodyDTO<ListOrderDTO> {

}