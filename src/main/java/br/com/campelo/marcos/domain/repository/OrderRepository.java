package br.com.campelo.marcos.domain.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.campelo.marcos.domain.entity.OrderEntity;
import br.com.campelo.marcos.dto.request.filter.FilterOrderListQueryParamDTO;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByControlNumberInAndClientId(List<BigInteger> controlNumbers, Long clientCode);

    @Query(value = "SELECT o "
			+ "       FROM OrderEntity o "
			+ "      WHERE 1 = 1 "
			+ "        AND (o.controlNumber = :#{#filter.controlNumber} OR :#{#filter.controlNumber} IS NULL) "
			+ "        AND (o.dateRegister = :#{#filter.dateRegister} OR :#{#filter.dateRegister} IS NULL) ")
	Page<OrderEntity> findOrderByFilter(@Param("filter") FilterOrderListQueryParamDTO filter, Pageable pageable);

}
