package br.com.campelo.marcos.exception.resolver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.campelo.marcos.exception.dto.BusinessErrorDTO;
import br.com.campelo.marcos.exception.dto.ErrorDTO;
import br.com.campelo.marcos.message.MessageErrorEnum;

public class ExceptionResolver implements ExceptionHandlerResolver<Exception> {

  @Override
  public ResponseEntity<BusinessErrorDTO<ErrorDTO>> doResolve(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
        new BusinessErrorDTO<>().withNewErrorList()
            .withErrorAdd(new ErrorDTO(MessageErrorEnum.MSG_ERROR_INTERNAL, e.getLocalizedMessage())).build());
  }
}