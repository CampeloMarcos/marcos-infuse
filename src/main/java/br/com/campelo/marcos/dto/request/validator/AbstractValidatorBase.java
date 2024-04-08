package br.com.campelo.marcos.dto.request.validator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;

import br.com.campelo.marcos.exception.BusinessException;
import br.com.campelo.marcos.message.MessageErrorEnum;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public abstract class AbstractValidatorBase<T> {

	private final List<String> validations;

	protected AbstractValidatorBase() {
		this.validations = new ArrayList<>();
	}

	protected boolean assertTrue(boolean condition) {
		return condition;
	}

	protected boolean assertNull(Object object) {
		return object == null;
	}

	protected boolean assertEmpty(String string) {
		return string == null || string.isEmpty();
	}

	public void validateAndThrow() {
		if (!this.validations.isEmpty()) {
			throw new BusinessException(MessageErrorEnum.MSG_ERR_NEGOCIO, this.validations);
		}
	}

	public void validateAndThrow(HttpStatus httpStatus) {
		if (!this.validations.isEmpty()) {
			throw new BusinessException(httpStatus, MessageErrorEnum.MSG_ERR_NEGOCIO, this.validations);
		}
	}

	protected void addValidation(String validation, Object... args) {
		log.warn(validation, args);
		this.validations.add(MessageFormatter.arrayFormat(validation, args).getMessage());
	}

}
