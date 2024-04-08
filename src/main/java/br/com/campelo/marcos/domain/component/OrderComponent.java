package br.com.campelo.marcos.domain.component;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.campelo.marcos.domain.entity.OrderEntity;
import br.com.campelo.marcos.domain.repository.OrderRepository;
import br.com.campelo.marcos.dto.request.filter.FilterOrderListQueryParamDTO;
import br.com.campelo.marcos.util.PaginationUtils;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class OrderComponent {

    private final OrderRepository repository;

    public List<OrderEntity> searchExistingControlNumberForCustomer(List<BigInteger> controlNumber, Long clientCode) {
        return repository.findByControlNumberInAndClientId(controlNumber, clientCode);
    }

    public void saveOrder(List<OrderEntity> entity) {
        repository.saveAll(entity);
    }

    public void saveOrder(OrderEntity entity) {
        repository.save(entity);
    }

    public Page<OrderEntity> searchOrderByFilter(FilterOrderListQueryParamDTO filter) {
		return repository.findOrderByFilter(filter, PaginationUtils.configPage(filter));
	}
}
