package me.conclure.cloncled.command;

public class CommandException extends RuntimeException {

  public CommandException() {
    super();
  }

  public CommandException(String message) {
    super(message);
  }

  public CommandException(String message, Throwable cause) {
    super(message, cause);
  }

  public CommandException(Throwable cause) {
    super(cause);
  }
}
