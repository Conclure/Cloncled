package me.conclure.cloncled.command.commands;

import me.conclure.cloncled.command.ChannelCommandListener;
import me.conclure.cloncled.command.Command;
import me.conclure.cloncled.command.ConsoleCommandListener;

import java.util.Set;

public abstract class DefaultCommand extends Command {
  private static final Set<Class<?>> ALLOWED_CLASSES = Set.of(ConsoleCommandListener.class, ChannelCommandListener.class);

  protected DefaultCommand(String name) {
    super(name, ALLOWED_CLASSES);
  }
}
