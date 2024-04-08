package br.com.campelo.marcos.exception.resolver;

import org.springframework.http.ResponseEntity;

import br.com.campelo.marcos.exception.dto.BusinessErrorDTO;
import br.com.campelo.marcos.exception.dto.ErrorDTO;

public interface ExceptionHandlerResolver<T> {

  ResponseEntity<BusinessErrorDTO<ErrorDTO>> doResolve(T t);
}
