package br.com.campelo.marcos.dto.request.validator;

import br.com.campelo.marcos.dto.request.enumeration.OrderRequestQueryParamEnum;
import br.com.campelo.marcos.message.MessageErrorEnum;
import br.com.campelo.marcos.message.MessageManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FilterRequestQueryParamValidatorDTO extends AbstractValidatorBase<FilterRequestQueryParamValidatorDTO> {

    private static final Integer ZERO = 0;

    public static FilterRequestQueryParamValidatorDTO builder() {
        return new FilterRequestQueryParamValidatorDTO();
    }

    public FilterRequestQueryParamValidatorDTO pageNotNull(Integer page) {
        if (assertNull(page)) {
            addValidation(MessageManager.getMessage(MessageErrorEnum.MSG_ERR_PAGINA_NAO_INFORMADA.getMessage()));
        }

        return this;
    }

    public FilterRequestQueryParamValidatorDTO quantityPerPageNotNull(Integer qtyPerPage) {
        if (assertNull(qtyPerPage)) {
            addValidation(MessageManager.getMessage(MessageErrorEnum.MSG_ERR_QUANTIDADE_POR_PAGINA_NAO_INFORMADA.getMessage()));
        }

        return this;
    }

    public FilterRequestQueryParamValidatorDTO fieldForOrderNotNull(String fieldOrdering) {
        if (assertEmpty(fieldOrdering)) {
            addValidation(MessageManager.getMessage(MessageErrorEnum.MSG_ERR_CAMPO_ORDENACAO_NAO_INFORMADO.getMessage()));
        }

        return this;
    }

    public FilterRequestQueryParamValidatorDTO fieldToSetDirectionOrderNotNull(OrderRequestQueryParamEnum ordering) {
        if (assertNull(ordering)) {
            addValidation(MessageManager.getMessage(MessageErrorEnum.MSG_ERR_DIRECAO_ORDENACAO_ASC_OU_DESC_NAO_INFORMADO.getMessage()));
        }

        return this;
    }

    public FilterRequestQueryParamValidatorDTO quantityPerPageLargestZero(Integer value) {
        if (assertTrue(value <= ZERO)) {
            addValidation(MessageManager.getMessage(MessageErrorEnum.MSG_ERR_QUANTIDADE_POR_PAGINA_MENOR_IGUAL_ZERO.getMessage()));
        }

        return this;
    }

    public FilterRequestQueryParamValidatorDTO quantityPerPageLesserOrEqualToMaximumLimit(Integer value, Integer limit) {
        if (assertTrue(value > limit)) {
            addValidation(MessageManager.getMessage(MessageErrorEnum.MSG_ERR_QUANTIDADE_POR_PAGINA_DEVE_SER_MENOR_IGUAL_MAXIMO_PAR1.getMessage(), limit));
        }

        return this;
    }
}
