package br.com.campelo.marcos.exception.resolver;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.campelo.marcos.exception.dto.BusinessErrorDTO;
import br.com.campelo.marcos.exception.dto.ErrorDTO;
import br.com.campelo.marcos.message.MessageErrorEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ConstraintViolationResolver implements ExceptionHandlerResolver<ConstraintViolationException> {

	@Override
	public ResponseEntity<BusinessErrorDTO<ErrorDTO>> doResolve(ConstraintViolationException ex) {
		final Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		MessageErrorEnum messageErrorEnum = null;
		for (final ConstraintViolation<?> violation : violations) {
			messageErrorEnum = MessageErrorEnum.valueOf(violation.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new BusinessErrorDTO<>().withNewErrorList()
				.withErrorAdd(new ErrorDTO(String.valueOf(HttpStatus.NOT_FOUND.value()), messageErrorEnum, messageErrorEnum)));
	}
}
