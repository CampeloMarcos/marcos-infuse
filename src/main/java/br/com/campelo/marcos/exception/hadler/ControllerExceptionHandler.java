package br.com.campelo.marcos.exception.hadler;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.campelo.marcos.exception.BusinessException;
import br.com.campelo.marcos.exception.dto.ErrorDTO;
import br.com.campelo.marcos.exception.resolver.BusinessExceptionResolver;
import br.com.campelo.marcos.exception.resolver.ConstraintViolationResolver;
import br.com.campelo.marcos.exception.resolver.ExceptionHandlerResolver;
import br.com.campelo.marcos.exception.resolver.ExceptionResolver;
import br.com.campelo.marcos.exception.resolver.MethodArgumentNotValidExceptionResolver;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

	private final BusinessExceptionResolver businessExceptionResolver;
	private final ExceptionResolver exceptionResolver;
	private final ConstraintViolationResolver constraintViolationResolver;
	private final MethodArgumentNotValidExceptionResolver methodArgumentNotValidExceptionResolver;

	private Map<Class, ExceptionHandlerResolver> handledExceptions() {
		return Stream
				.of(new AbstractMap.SimpleImmutableEntry<>(BusinessException.class, businessExceptionResolver),
						new AbstractMap.SimpleImmutableEntry<>(MethodArgumentNotValidException.class,
								methodArgumentNotValidExceptionResolver),
						new AbstractMap.SimpleImmutableEntry<>(ConstraintViolationException.class,
								constraintViolationResolver))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}

	@ExceptionHandler({ BusinessException.class, MethodArgumentNotValidException.class,
			ConstraintViolationException.class, Exception.class })
	public ResponseEntity<ErrorDTO> handleException(Exception e) {
		return handleMappedException(e);
	}

	public ResponseEntity<ErrorDTO> handleMappedException(Exception e) {
		Entry<Class, ExceptionHandlerResolver> mapException = handledExceptions().entrySet().stream() //
				.filter(obj -> obj.getKey().isInstance(e)).findAny() //
				.orElseGet(() -> new AbstractMap.SimpleImmutableEntry<>(Exception.class, exceptionResolver));
		return mapException.getValue().doResolve(e);
	}
}
