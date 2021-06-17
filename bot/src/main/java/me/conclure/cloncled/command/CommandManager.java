package me.conclure.cloncled.command;

import net.dv8tion.jda.api.JDA;

import me.conclure.cloncled.bootstrap.ShutdownSignal;

public final class CommandManager {
  private final ShutdownSignal shutdownSignal;
  private final ConsoleCommandListener consoleCommandListener;
  private final ChannelCommandListener channelCommandListener;

  public CommandManager(JDA jda, ShutdownSignal shutdownSignal) {
    this.shutdownSignal = shutdownSignal;
    this.consoleCommandListener = new ConsoleCommandListener(shutdownSignal);
    this.channelCommandListener = new ChannelCommandListener(shutdownSignal);

    jda.addEventListener(this.channelCommandListener);
  }
}
