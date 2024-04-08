package br.com.campelo.marcos.message;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.helpers.MessageFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageManager {

  private static final ResourceBundle resourceMessage = ResourceBundle.getBundle("message");

  public static String getMessage(String message, Object... args) {
    String text;

    try {
      text = message == null ? "" : resourceMessage.getString(message);
      text = MessageFormatter.arrayFormat(text, args).getMessage();
    } catch (MissingResourceException ex) {
      log.trace("Erro: {}", ex.getMessage(), ex);
      text = message;
    }
    return text;
  }
}