package me.conclure.cloncled.command.manager;

import me.conclure.cloncled.bootstrap.ShutdownSignal;
import me.conclure.cloncled.command.Command;
import me.conclure.cloncled.command.context.CommandContext;
import me.conclure.cloncled.command.listener.ConsoleCommandListener;

import java.util.stream.Stream;

public class ConsoleCommandManager extends AbstractCommandManager<CommandContext> {

  public ConsoleCommandManager(
      ShutdownSignal shutdownSignal
  ) {
    super(
        Stream.of()
    );
    new ConsoleCommandListener(shutdownSignal);
  }
}
