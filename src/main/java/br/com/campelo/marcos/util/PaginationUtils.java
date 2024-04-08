package br.com.campelo.marcos.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import br.com.campelo.marcos.dto.request.abstracts.AbstractFilterRequestQueryParamDTO;
import br.com.campelo.marcos.dto.request.enumeration.OrderRequestQueryParamEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PaginationUtils {

  public static Pageable configPage(AbstractFilterRequestQueryParamDTO filter) {
    Sort sort;
    if (filter.getFieldOrdering() != null && !"".equals(filter.getFieldOrdering())) {
      final List<Order> orders = Arrays.stream(
              filter.getFieldOrdering().split(String.valueOf(",")))
          .map(c -> fieldOrder(filter, c)).collect(Collectors.toList());
      sort = Sort.by(orders);
    } else {
      sort = Sort.unsorted();
    }
    int pag = filter.getPage() - 1;
    if (pag < 0) {
      pag = 0;
    }
    return PageRequest.of(pag, filter.getQtdByPage(), sort);
  }

  private static Order fieldOrder(AbstractFilterRequestQueryParamDTO filter,
      String fieldOrdering) {
    if (filter.getOrdering() == null
        || filter.getOrdering() == OrderRequestQueryParamEnum.ASC) {
      return Sort.Order.asc(fieldOrdering.trim());
    }
    return Sort.Order.desc(fieldOrdering.trim());
  }
}
