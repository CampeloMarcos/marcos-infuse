package br.com.campelo.marcos.dto.request.filter;

import java.beans.ConstructorProperties;
import java.math.BigInteger;
import java.time.LocalDate;

import br.com.campelo.marcos.dto.request.abstracts.AbstractFilterRequestQueryParamDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterOrderListQueryParamDTO extends AbstractFilterRequestQueryParamDTO {

	private BigInteger controlNumber;
	private LocalDate dateRegister;

	@ConstructorProperties({"control-number","date-register", "qtd-by-page", "field-ordering"})
	public FilterOrderListQueryParamDTO(BigInteger controlNumber, LocalDate dateRegister, Integer qtdByPage, String fieldOrdering) {
		this.controlNumber = controlNumber;
		this.dateRegister = dateRegister;
		if(qtdByPage != null && qtdByPage != 0) {
			super.setQtdByPage(qtdByPage);
		}
		if(fieldOrdering != null && !"".equals(fieldOrdering)) {
			super.setFieldOrdering(fieldOrdering);
		}
	}
	
}