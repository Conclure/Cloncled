package me.conclure.cloncled.command.context;

import me.conclure.cloncled.command.Arguments;

public class CommandContext {
  private final Arguments arguments;

  public CommandContext(Arguments arguments) {
    this.arguments = arguments;
  }

  public Arguments arguments() {
    return this.arguments;
  }
}
