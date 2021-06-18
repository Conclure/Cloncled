package me.conclure.cloncled.command;

import me.conclure.cloncled.command.context.CommandContext;

public abstract class Command<C extends CommandContext> {
  private final String name;

  protected Command(String name) {
    this.name = name.toLowerCase();
  }

  public final String name() {
    return this.name;
  }

  protected abstract void execute(C context) throws Exception;
}
