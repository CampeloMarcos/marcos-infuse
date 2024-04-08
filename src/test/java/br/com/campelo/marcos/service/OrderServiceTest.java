package br.com.campelo.marcos.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.campelo.marcos.domain.component.ClientComponent;
import br.com.campelo.marcos.domain.component.OrderComponent;
import br.com.campelo.marcos.domain.entity.ClientEntity;
import br.com.campelo.marcos.domain.entity.OrderEntity;
import br.com.campelo.marcos.dto.request.CreateOrderDTO;
import br.com.campelo.marcos.dto.request.OrderDTO;
import br.com.campelo.marcos.dto.request.filter.FilterOrderListQueryParamDTO;
import br.com.campelo.marcos.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService service;

    @Mock
    private OrderComponent orderComponent;
    @Mock
    private ClientComponent clientComponent;

    @Test
    public void createOrderTest() {
        CreateOrderDTO dto = new CreateOrderDTO();
        List<OrderDTO> orders = new ArrayList<>();
        OrderDTO order = new OrderDTO();
        order.setAmount(1);
        order.setPrice(BigDecimal.TEN);
        order.setControlNumber(BigInteger.ONE);
        orders.add(order);
        dto.setOrders(orders);
        dto.setClientCode(1L);
        when(orderComponent.searchExistingControlNumberForCustomer(anyList(), anyLong())).thenReturn(new ArrayList<>());
        when(clientComponent.findClientId(anyLong())).thenReturn(Optional.of(new ClientEntity()));
        doNothing().when(orderComponent).saveOrder(anyList());
        service.createOrder(dto);
        verify(orderComponent, times(1)).saveOrder(anyList());
    }

    @Test
    public void createOrder2Test() {
        CreateOrderDTO dto = new CreateOrderDTO();
        List<OrderDTO> orders = new ArrayList<>();
        OrderDTO order = new OrderDTO();
        order.setAmount(5);
        order.setPrice(BigDecimal.TEN);
        order.setControlNumber(BigInteger.ONE);
        orders.add(order);
        dto.setOrders(orders);
        dto.setClientCode(1L);
        when(orderComponent.searchExistingControlNumberForCustomer(anyList(), anyLong())).thenReturn(new ArrayList<>());
        when(clientComponent.findClientId(anyLong())).thenReturn(Optional.of(new ClientEntity()));
        doNothing().when(orderComponent).saveOrder(anyList());
        service.createOrder(dto);
        verify(orderComponent, times(1)).saveOrder(anyList());
    }

    @Test
    public void createOrder3Test() {
        CreateOrderDTO dto = new CreateOrderDTO();
        List<OrderDTO> orders = new ArrayList<>();
        OrderDTO order = new OrderDTO();
        order.setAmount(11);
        order.setPrice(BigDecimal.TEN);
        order.setControlNumber(BigInteger.ONE);
        orders.add(order);
        dto.setOrders(orders);
        dto.setClientCode(1L);
        when(orderComponent.searchExistingControlNumberForCustomer(anyList(), anyLong())).thenReturn(new ArrayList<>());
        when(clientComponent.findClientId(anyLong())).thenReturn(Optional.of(new ClientEntity()));
        doNothing().when(orderComponent).saveOrder(anyList());
        service.createOrder(dto);
        verify(orderComponent, times(1)).saveOrder(anyList());
    }

    @Test
    public void createOrderExceptionTest() {
        CreateOrderDTO dto = mock(CreateOrderDTO.class);
        List<OrderEntity> orders = new ArrayList<>();
        orders.add(new OrderEntity());
        when(orderComponent.searchExistingControlNumberForCustomer(anyList(), anyLong())).thenReturn(orders);
        assertThrows(BusinessException.class, () -> service.createOrder(dto));
    }

    @Test
    public void createOrderException2Test() {
        CreateOrderDTO dto = mock(CreateOrderDTO.class);
        when(orderComponent.searchExistingControlNumberForCustomer(anyList(), anyLong())).thenReturn(new ArrayList<>());
        when(clientComponent.findClientId(anyLong())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> service.createOrder(dto));
    }

    @Test
    public void listOrderTest() {
        assertDoesNotThrow(() -> {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setClient(new ClientEntity());
            Page<OrderEntity> page = new PageImpl<>(List.of(orderEntity), mock(Pageable.class), 0);
            when(orderComponent.searchOrderByFilter(any())).thenReturn(page);
            service.listOrder(mock(FilterOrderListQueryParamDTO.class));
        });
    }
}
