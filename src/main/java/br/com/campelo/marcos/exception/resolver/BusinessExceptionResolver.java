package br.com.campelo.marcos.exception.resolver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.campelo.marcos.exception.BusinessException;
import br.com.campelo.marcos.exception.dto.BusinessErrorDTO;
import br.com.campelo.marcos.exception.dto.ErrorDTO;

public class BusinessExceptionResolver implements ExceptionHandlerResolver<BusinessException> {

  @Override
  public ResponseEntity<BusinessErrorDTO<ErrorDTO>> doResolve(BusinessException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBusinessErrors());
  }

}
