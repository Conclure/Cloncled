package me.conclure.cloncled.command;

import net.dv8tion.jda.api.JDA;

import me.conclure.cloncled.bootstrap.ShutdownSignal;
import me.conclure.cloncled.configuration.Configuration;

public final class CommandManager {
  private final CommandDispatcher commandDispatcher;
  private final CommandMap commandMap;
  private final ShutdownSignal shutdownSignal;
  private final ConsoleCommandListener consoleCommandListener;
  private final ChannelCommandListener channelCommandListener;
  private final Configuration configuration;

  public CommandManager(JDA jda, ShutdownSignal shutdownSignal,
      Configuration configuration) {
    this.shutdownSignal = shutdownSignal;
    this.configuration = configuration;
    this.commandMap = new CommandMap();
    this.commandDispatcher = new CommandDispatcher(shutdownSignal);
    this.consoleCommandListener = new ConsoleCommandListener(shutdownSignal);
    this.channelCommandListener = new ChannelCommandListener(this.commandMap, shutdownSignal, this.configuration,
        this.commandDispatcher);

    jda.addEventListener(this.channelCommandListener);
  }
}
