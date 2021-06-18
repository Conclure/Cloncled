package me.conclure.cloncled.command;

import me.conclure.cloncled.api.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandMap {
  private final Map<String,Command> commandMap;

  public CommandMap() {
    this.commandMap = new HashMap<>();
  }

  public void register(Command command) {
    this.commandMap.put(command.name(),command);
  }

  public void register(Iterable<Command> commands) {
    for (Command command : commands) {
      this.register(command);
    }
  }

  public void register(Command command, Command... commands) {
    this.register(command);
    this.register(List.of(commands));
  }

  @Nullable
  public Command getCommand(String name) {
    return this.commandMap.get(name.toLowerCase());
  }
}
