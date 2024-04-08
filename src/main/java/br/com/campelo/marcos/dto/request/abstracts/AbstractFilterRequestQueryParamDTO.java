package br.com.campelo.marcos.dto.request.abstracts;

import br.com.campelo.marcos.dto.request.enumeration.OrderRequestQueryParamEnum;
import br.com.campelo.marcos.dto.request.validator.FilterRequestQueryParamValidatorDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class AbstractFilterRequestQueryParamDTO {

  private static final Integer QTD_MAX_POR_PAGINA = 100;
  private static final Integer QTD_POR_PAGINA = 10;
  private static final Integer NUMERO_PAGINA = 1;

  private Integer page = NUMERO_PAGINA;

  private Integer qtdByPage = QTD_POR_PAGINA;

  private String fieldOrdering;

  private OrderRequestQueryParamEnum ordering;
  
  protected AbstractFilterRequestQueryParamDTO(){}

  protected AbstractFilterRequestQueryParamDTO(Integer qtdByPage, String fieldOrdering) {
    this.qtdByPage = qtdByPage;
    this.fieldOrdering = fieldOrdering;
  }

  public void setPage(Integer page) {
    FilterRequestQueryParamValidatorDTO.builder().pageNotNull(page).validateAndThrow();
    this.page = page;
  }

  public void setQtdByPage(Integer qtdByPage) {
    FilterRequestQueryParamValidatorDTO.builder().quantityPerPageLargestZero(qtdByPage)
        .quantityPerPageLesserOrEqualToMaximumLimit(qtdByPage, QTD_MAX_POR_PAGINA)
        .validateAndThrow();
    this.qtdByPage = qtdByPage;
  }

  public void setFieldOrdering(String fieldOrdering) {
    FilterRequestQueryParamValidatorDTO.builder().fieldForOrderNotNull(fieldOrdering)
        .validateAndThrow();
    this.fieldOrdering = fieldOrdering;
  }

  public void setOrdering(OrderRequestQueryParamEnum ordering) {
    FilterRequestQueryParamValidatorDTO.builder().fieldToSetDirectionOrderNotNull(ordering)
        .validateAndThrow();
    this.ordering = ordering;
  }

}
