package me.conclure.cloncled.command;

import me.conclure.cloncled.api.annotations.Nullable;
import me.conclure.cloncled.command.context.CommandContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandMap<C extends CommandContext> {
  private final Map<String,Command<? super C>> commandMap;

  public CommandMap() {
    this.commandMap = new HashMap<>();
  }

  public void register(Command<? super C> command) {
    this.commandMap.put(command.name(),command);
  }

  @Nullable
  public Command<? super C> getCommand(String name) {
    return this.commandMap.get(name.toLowerCase());
  }
}
