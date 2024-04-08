package br.com.campelo.marcos.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.campelo.marcos.dto.request.CreateOrderDTO;
import br.com.campelo.marcos.dto.request.filter.FilterOrderListQueryParamDTO;
import br.com.campelo.marcos.dto.response.ListOrderResponseDTO;
import br.com.campelo.marcos.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @InjectMocks
	private OrderController controller;
	
	@Mock
	private OrderService service;

    @Test
    public void createOrderTest() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
        CreateOrderDTO dto = mock(CreateOrderDTO.class);
        doNothing().when(service).createOrder(dto);
		
		ResponseEntity<?> responseEntity = controller.createOrder(dto);
		
		assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);
    }

    @Test
    public void findOrderTest() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
        FilterOrderListQueryParamDTO dto = mock(FilterOrderListQueryParamDTO.class);
        when(service.listOrder(dto)).thenReturn(mock(ListOrderResponseDTO.class));
		
		ResponseEntity<?> responseEntity = controller.listOrder(dto);
		
		assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
    }
}
