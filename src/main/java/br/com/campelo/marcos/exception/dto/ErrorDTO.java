package br.com.campelo.marcos.exception.dto;

import java.io.Serializable;

import br.com.campelo.marcos.message.MessageErrorEnum;
import br.com.campelo.marcos.message.MessageManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO implements Serializable {

    private String code;
    private String message;
    private String detailedMessage;

    public ErrorDTO(String code, String message, String detailedMessage) {
		this.code = code;
		this.message = message;
		this.detailedMessage = detailedMessage;
	}

	public ErrorDTO(MessageErrorEnum messageErrorEnum) {
		this.code = messageErrorEnum.getCode();
		this.message = MessageManager.getMessage(messageErrorEnum.getMessage());
		this.detailedMessage = MessageManager.getMessage(messageErrorEnum.getDetailedMessage());
	}

	public ErrorDTO(MessageErrorEnum messageErrorEnum, Object... args) {
		this.code = messageErrorEnum.getCode();
		this.message = MessageManager.getMessage(messageErrorEnum.getMessage(), args);
		this.detailedMessage = MessageManager.getMessage(messageErrorEnum.getDetailedMessage(), args);
	}

	public ErrorDTO(String code, MessageErrorEnum message, MessageErrorEnum detailedMessage) {
		this.code = code;
		this.message = MessageManager.getMessage(message.getMessage());
		this.detailedMessage = MessageManager.getMessage(detailedMessage.getMessage());
	}

	public ErrorDTO(String message, String detailedMessage, Object... args) {
		this.message = message;
		this.detailedMessage = MessageManager.getMessage(detailedMessage, args);
	}

}
