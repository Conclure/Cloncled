package me.conclure.cloncled.command.manager;

import net.dv8tion.jda.api.JDA;

import me.conclure.cloncled.bootstrap.BotConfig;
import me.conclure.cloncled.bootstrap.ShutdownSignal;
import me.conclure.cloncled.command.Command;
import me.conclure.cloncled.command.CommandDispatcher;
import me.conclure.cloncled.command.context.DiscordCommandContext;
import me.conclure.cloncled.command.listener.DiscordCommandListener;
import me.conclure.cloncled.commands.discord.PingCommand;
import me.conclure.cloncled.configuration.Configuration;

import java.util.stream.Stream;

public class DiscordCommandManager extends AbstractCommandManager<DiscordCommandContext> {

  public DiscordCommandManager(
      ShutdownSignal shutdownSignal,
      BotConfig botConfig,
      CommandDispatcher commandDispatcher,
      JDA jda
  ) {
    super(
        Stream.of(
            new PingCommand()
        )
    );
    Object listener = new DiscordCommandListener(this.commandMap, shutdownSignal,
        botConfig, commandDispatcher);
    jda.addEventListener(listener);
  }

}
