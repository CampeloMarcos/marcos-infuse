package br.com.campelo.marcos.exception;

import java.util.List;
import org.springframework.http.HttpStatus;

import br.com.campelo.marcos.exception.dto.BusinessErrorDTO;
import br.com.campelo.marcos.exception.dto.ErrorDTO;
import br.com.campelo.marcos.message.MessageErrorEnum;
import br.com.campelo.marcos.message.MessageManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

  private final String message;
  private final HttpStatus httpStatus;
  private final BusinessErrorDTO<ErrorDTO> businessErrors;

  public BusinessException(MessageErrorEnum messageErrorEnum) {
    final ErrorDTO erroDTO = new ErrorDTO(messageErrorEnum);
    this.message = erroDTO.getDetailedMessage();
    this.httpStatus = HttpStatus.BAD_REQUEST;
    this.businessErrors = new BusinessErrorDTO<>().withNewErrorList().withErrorAdd(erroDTO).build();
  }

  public BusinessException(MessageErrorEnum messageErrorEnum, Object... args) {
    final ErrorDTO erroDTO = new ErrorDTO(messageErrorEnum, args);
    this.message = erroDTO.getDetailedMessage();
    this.httpStatus = HttpStatus.BAD_REQUEST;
    this.businessErrors = new BusinessErrorDTO<>().withNewErrorList().withErrorAdd(erroDTO).build();
  }

  public BusinessException(String code, String titulo, String erro) {
    HttpStatus http;
    final ErrorDTO erroDTO = new ErrorDTO(code, titulo, erro);
    this.message = erroDTO.getDetailedMessage();
    try {
      http = HttpStatus.valueOf(code);
    } catch (final IllegalArgumentException e) {
      http = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    this.httpStatus = http;
    this.businessErrors = new BusinessErrorDTO<>().withNewErrorList().withErrorAdd(erroDTO).build();
  }

  public BusinessException(HttpStatus http, MessageErrorEnum messageErrorEnum, Object... args) {
    final ErrorDTO erroDTO = new ErrorDTO(messageErrorEnum, args);
    this.message = erroDTO.getDetailedMessage();
    this.httpStatus = http;
    this.businessErrors = new BusinessErrorDTO<>().withNewErrorList().withErrorAdd(erroDTO).build();
  }

  /*
   * public BusinessException(HttpStatus http, MessageErrorEnum messageErrorEnum)
   * {
   * final ErrorDTO erroDTO = new ErrorDTO(String.valueOf(http.value()),
   * messageErrorEnum.getMessage());
   * this.message = erroDTO.getDetailedMessage();
   * this.httpStatus = http;
   * this.businessErrors = new
   * BusinessErrorDTO<>().withNewErrorList().withErrorAdd(erroDTO).build();
   * }
   */

  public BusinessException(HttpStatus http, MessageErrorEnum messageErrorEnum,
      Throwable throwable) {
    super(throwable);
    final ErrorDTO erroDTO = new ErrorDTO(String.valueOf(http.value()), messageErrorEnum.getMessage(),
        throwable.getMessage());
    this.message = throwable.getMessage();
    this.httpStatus = http;
    this.businessErrors = new BusinessErrorDTO<>().withNewErrorList().withErrorAdd(erroDTO).build();
  }

  public BusinessException(MessageErrorEnum error, List<String> mensagens) {
    this.httpStatus = HttpStatus.BAD_REQUEST;
    this.businessErrors = new BusinessErrorDTO<>().withNewErrorList().build();
    this.message = MessageManager.getMessage(error.getMessage());

    mensagens.stream()
        .map(msg -> new ErrorDTO(String.valueOf(error.getCode()), error.getMessage(), msg))
        .forEach(this.businessErrors::withErrorAdd);
  }

  public BusinessException(HttpStatus httpStatus, MessageErrorEnum error, List<String> mensagens) {
    this.httpStatus = httpStatus;
    this.businessErrors = new BusinessErrorDTO<>().withNewErrorList().build();
    this.message = MessageManager.getMessage(error.getMessage());

    mensagens.stream().map(msg -> new ErrorDTO(String.valueOf(httpStatus.value()),
        MessageManager.getMessage(error.getMessage()), msg))
        .forEach(this.businessErrors::withErrorAdd);
  }

  public BusinessException(HttpStatus http, MessageErrorEnum messageErrorEnum, String resource) {
    final ErrorDTO erroDTO = new ErrorDTO(messageErrorEnum.getCode(),
        MessageManager.getMessage(messageErrorEnum.getMessage(), resource),
        MessageManager.getMessage(messageErrorEnum.getDetailedMessage(), resource));

    this.message = erroDTO.getDetailedMessage();
    this.httpStatus = http;
    this.businessErrors = new BusinessErrorDTO<>().withNewErrorList().withErrorAdd(erroDTO).build();
  }

  public BusinessException(MessageErrorEnum messageErrorEnum, String message) {
    HttpStatus http;
    final ErrorDTO erroDTO = new ErrorDTO(messageErrorEnum.getCode(), messageErrorEnum.getMessage(),
        message);
    try {
      http = HttpStatus.valueOf(erroDTO.getCode());
    } catch (final IllegalArgumentException e) {
      http = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    this.httpStatus = http;
    this.message = erroDTO.getDetailedMessage();
    this.businessErrors = new BusinessErrorDTO<>().withNewErrorList().withErrorAdd(erroDTO).build();
  }
}
