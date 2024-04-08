package br.com.campelo.marcos.dto.parse;

import br.com.campelo.marcos.domain.entity.ClientEntity;
import br.com.campelo.marcos.domain.entity.OrderEntity;
import br.com.campelo.marcos.dto.request.OrderDTO;

public class OrderDTOPase {

    public static OrderEntity toPedidoEntity(OrderDTO orderDTO, Long clientCode) {
        OrderEntity entity = new OrderEntity();
        entity.setClient(new ClientEntity(clientCode));
        entity.setDateRegister(orderDTO.getDateRegister());
        entity.setProductName(orderDTO.getName());
        entity.setControlNumber(orderDTO.getControlNumber());
        entity.setAmount(orderDTO.getAmount());
        entity.setPrice(orderDTO.getPrice());        
        return entity;
    }

    
}
