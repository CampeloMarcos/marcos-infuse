package br.com.campelo.marcos.exception.resolver;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.campelo.marcos.exception.dto.BusinessErrorDTO;
import br.com.campelo.marcos.exception.dto.ErrorDTO;
import br.com.campelo.marcos.message.MessageErrorEnum;

public class MethodArgumentNotValidExceptionResolver
    implements ExceptionHandlerResolver<MethodArgumentNotValidException> {

  @Override
  public ResponseEntity<BusinessErrorDTO<ErrorDTO>> doResolve(
      MethodArgumentNotValidException methodArgumentNotValidException) {
    final BusinessErrorDTO<ErrorDTO> businessErrorDTO =
        new BusinessErrorDTO<>().withNewErrorList().build();

    methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(fieldError -> {
      final MessageErrorEnum messageErrorEnum =
          MessageErrorEnum.getByMessage(fieldError.getDefaultMessage());

      if (Objects.nonNull(messageErrorEnum)) {
        businessErrorDTO
            .withErrorAdd(new ErrorDTO(messageErrorEnum, fieldError.getField()));
      } else {
        businessErrorDTO.withErrorAdd(
            new ErrorDTO("0", fieldError.getDefaultMessage(), fieldError.getDefaultMessage()));
      }
    });

    if (!methodArgumentNotValidException.getBindingResult().getFieldErrors().isEmpty()
        && methodArgumentNotValidException.getBindingResult().getFieldErrors().size() > 1) {

      final Map<String, List<FieldError>> mapErrors =
          methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
              .collect(Collectors.groupingBy(DefaultMessageSourceResolvable::getDefaultMessage));

      businessErrorDTO.getErrors().clear();

      mapErrors.forEach((detail, fieldErrorsList) -> {
        final Optional<FieldError> opFieldError = fieldErrorsList.stream().findFirst();
        opFieldError.ifPresent(fieldError -> {
          final MessageErrorEnum messageEnum =
              MessageErrorEnum.getByMessageRefMessageProperties(fieldError.getDefaultMessage());

          final ErrorDTO error = new ErrorDTO(messageEnum,
              fieldErrorsList.stream().map(FieldError::getField).collect(Collectors.joining(", ")));

          businessErrorDTO.withErrorAdd(error);
        });
      });
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(businessErrorDTO);
  }
}