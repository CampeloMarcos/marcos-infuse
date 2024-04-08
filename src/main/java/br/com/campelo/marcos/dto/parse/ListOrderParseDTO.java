package br.com.campelo.marcos.dto.parse;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import br.com.campelo.marcos.domain.entity.OrderEntity;
import br.com.campelo.marcos.dto.response.ListOrderDTO;
import br.com.campelo.marcos.dto.response.ListOrderResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ListOrderParseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static ListOrderResponseDTO toOrderListDTOResponse(Page<OrderEntity> page) {
		ListOrderResponseDTO response = new ListOrderResponseDTO();

		response.setPageNumber(page.getNumber() + 1);
		response.setHasPrevious(page.hasPrevious());
		response.setHasNext(page.hasNext());
		response.setTotalPages(page.getTotalPages());
		response.setTotalElements(page.getTotalElements());
		
		page.getContent().stream().map(ListOrderParseDTO::toListOrderDTO)
        .forEach(response.getContent()::add);
		
		return response;
	}
	
	public static ListOrderDTO toListOrderDTO(OrderEntity entity) {
		ListOrderDTO dto = new ListOrderDTO();
		dto.setAmount(entity.getAmount());
		dto.setClientCode(entity.getClient().getId());
		dto.setClientName(entity.getClient().getClientName());
		dto.setControlNumber(entity.getControlNumber());
		dto.setDateRegister(entity.getDateRegister());
		dto.setName(entity.getProductName());
		dto.setPrice(entity.getPrice());
		dto.setTotalOrderPrice(entity.getTotalOrderPrice());
		return dto;
	}
}
