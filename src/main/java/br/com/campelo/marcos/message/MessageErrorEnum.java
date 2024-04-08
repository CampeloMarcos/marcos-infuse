package br.com.campelo.marcos.message;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageErrorEnum {

    MSG_ERROR_REGISTER_CONTROL_NUMBER_FOUND("1", "msg.error.register.found", "msg.error.register.number.control"),
    MSG_ERROR_FIELD_REQUIRED("2", "msg.error.field.required", "msg.error.field.required.detailed"),
    MSG_ERROR_FIELD_SIZE("3", "msg.error.field.size", "msg.error.field.size.detailed"),
    MSG_ERROR_REGISTER_CLIENT_NOT_FOUND("3", "msg.error.register.not.found", "msg.error.register.client.not.found"),
    MSG_ERR_QUANTIDADE_POR_PAGINA_DEVE_SER_MENOR_IGUAL_MAXIMO_PAR1("", "", ""), 
    MSG_ERR_QUANTIDADE_POR_PAGINA_MENOR_IGUAL_ZERO("", "", ""), 
    MSG_ERR_DIRECAO_ORDENACAO_ASC_OU_DESC_NAO_INFORMADO("", "", ""), 
    MSG_ERR_CAMPO_ORDENACAO_NAO_INFORMADO("", "", ""), 
    MSG_ERR_QUANTIDADE_POR_PAGINA_NAO_INFORMADA("", "", ""), 
    MSG_ERR_PAGINA_NAO_INFORMADA("", "", ""),
    MSG_ERR_NEGOCIO("", "", ""),
    MSG_ERROR_INTERNAL("500", "msg.error.internal", "msg.error.internal.detailed");

    private final String code;
    private final String message;
    private final String detailedMessage;

    public static MessageErrorEnum getByCode(String codigo) {
        return Arrays.stream(MessageErrorEnum.values()).filter(messageError -> messageError.getCode().equals(codigo))
                .findFirst().orElse(null);
    }

    public static MessageErrorEnum getByMessage(String msg) {
        return Arrays.stream(MessageErrorEnum.values()).filter(messageError -> messageError.getMessage().equals(msg))
                .findFirst().orElse(null);
    }

    public static MessageErrorEnum getByMessageRefMessageProperties(String properties) {
        return Arrays.stream(MessageErrorEnum.values())
                .filter(messageError -> messageError.getMessage().equals(properties)).findFirst().orElse(null);
    }
}
