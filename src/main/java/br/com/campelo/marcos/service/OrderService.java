package br.com.campelo.marcos.service;

import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.campelo.marcos.domain.component.ClientComponent;
import br.com.campelo.marcos.domain.component.OrderComponent;
import br.com.campelo.marcos.domain.entity.ClientEntity;
import br.com.campelo.marcos.domain.entity.OrderEntity;
import br.com.campelo.marcos.dto.parse.ListOrderParseDTO;
import br.com.campelo.marcos.dto.parse.OrderDTOPase;
import br.com.campelo.marcos.dto.request.CreateOrderDTO;
import br.com.campelo.marcos.dto.request.OrderDTO;
import br.com.campelo.marcos.dto.request.filter.FilterOrderListQueryParamDTO;
import br.com.campelo.marcos.dto.response.ListOrderResponseDTO;
import br.com.campelo.marcos.exception.BusinessException;
import br.com.campelo.marcos.message.MessageErrorEnum;
import br.com.campelo.marcos.util.MathUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class OrderService {

    private static final BigDecimal FIVE_PER_CENT = new BigDecimal("0.05");
    private static final BigDecimal TEN_PER_CENT = new BigDecimal("0.10");
    
    private final OrderComponent orderComponent;
    private final ClientComponent clientComponent;

    public void createOrder(@Valid CreateOrderDTO createOrderDTO) {
        this.validateControlNumber(createOrderDTO);
        this.validateClient(createOrderDTO.getClientCode());
        orderComponent.saveOrder(
                parseOrderEntity(createOrderDTO.getOrders(), createOrderDTO.getClientCode()));
    }

    public ListOrderResponseDTO listOrder(FilterOrderListQueryParamDTO filterOrder) {        
        return ListOrderParseDTO.toOrderListDTOResponse(orderComponent.searchOrderByFilter(filterOrder));
    }  

    private void validateControlNumber(CreateOrderDTO createOrderDTO) {
        final List<BigInteger> controlNumbers = createOrderDTO.getOrders().stream().map(OrderDTO::getControlNumber)
                .collect(Collectors.toList());
        final List<OrderEntity> existingOrders = orderComponent.searchExistingControlNumberForCustomer(
                controlNumbers,
                createOrderDTO.getClientCode());
        if (!existingOrders.isEmpty()) {
            throw new BusinessException(MessageErrorEnum.MSG_ERROR_REGISTER_CONTROL_NUMBER_FOUND,
            existingOrders.stream().map(OrderEntity::getControlNumber).collect(Collectors.toList()),
                    createOrderDTO.getClientCode());
        }
    }

    private void validateClient(Long clientCode) {
        Optional<ClientEntity> opClient = clientComponent.findClientId(clientCode);
        if(!opClient.isPresent()) {
            throw new BusinessException(MessageErrorEnum.MSG_ERROR_REGISTER_CLIENT_NOT_FOUND, clientCode);
        }
    }

    private List<OrderEntity> parseOrderEntity(List<OrderDTO> ordersDTO, Long clientCode) {
        return ordersDTO.stream()
                .map(order -> {
                    OrderEntity entity = OrderDTOPase.toPedidoEntity(order, clientCode);
                    BigDecimal totalOrderPrice = entity.getPrice().multiply(BigDecimal.valueOf(entity.getAmount()));
                    entity.setTotalOrderPrice(calculateDiscount(totalOrderPrice, entity.getAmount()));
                    return entity;
                })
                .collect(Collectors.toList());
    }

    private static BigDecimal calculateDiscount(BigDecimal priceOrder, Integer amount) {
        if (amount < 5) {
            return priceOrder;
        }
        if (amount >= 5 && amount <= 9) {
            return MathUtil.calculateDiscount(priceOrder, FIVE_PER_CENT);
        }
        return MathUtil.calculateDiscount(priceOrder, TEN_PER_CENT);
    }  
}
