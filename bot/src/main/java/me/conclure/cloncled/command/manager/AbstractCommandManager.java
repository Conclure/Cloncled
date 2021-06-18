package me.conclure.cloncled.command.manager;

import me.conclure.cloncled.command.Command;
import me.conclure.cloncled.command.CommandMap;
import me.conclure.cloncled.command.context.CommandContext;

import java.util.stream.Stream;

public abstract class AbstractCommandManager<C extends CommandContext> {
  protected final CommandMap<C> commandMap;

  protected AbstractCommandManager(Stream<Command<C>> commands) {
    this.commandMap = new CommandMap<>();
    commands.forEach(this.commandMap::register);
  }

  public CommandMap<C> commandMap() {
    return this.commandMap;
  }
}
