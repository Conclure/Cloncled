package me.conclure.cloncled.command;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

import me.conclure.cloncled.bootstrap.ShutdownSignal;
import me.conclure.cloncled.bot.ConfigKeys;
import me.conclure.cloncled.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public final class ChannelCommandListener {
  private final CommandMap commandMap;
  private final ShutdownSignal shutdownSignal;
  private final Configuration configuration;
  private final CommandDispatcher dispatcher;

  public ChannelCommandListener(CommandMap commandMap, ShutdownSignal shutdownSignal,
      Configuration configuration, CommandDispatcher dispatcher) {
    this.commandMap = commandMap;
    this.shutdownSignal = shutdownSignal;
    this.configuration = configuration;
    this.dispatcher = dispatcher;
  }

  @SubscribeEvent
  public void onMessageReceived(MessageReceivedEvent event) {
    if (this.shutdownSignal.signalsShutdown()) {
      event.getJDA().removeEventListener(this);
      return;
    }

    User author = event.getAuthor();

    if (author.isBot()) {
      return;
    }

    if (author.isSystem()) {
      return;
    }

    Message message = event.getMessage();

    if (message.isWebhookMessage()) {
      return;
    }

    if (message.isTTS()) {
      return;
    }

    String contentRaw = message.getContentRaw();
    String prefix = this.configuration.get(ConfigKeys.PREFIX);

    if (!contentRaw.startsWith(prefix)) {
      return;
    }

    contentRaw = contentRaw.substring(prefix.length() - 1);

    String[] splitContent = contentRaw.split(" ");

    if (splitContent.length == 0) {
      return;
    }

    List<String> commandLine = new ArrayList<>();

    for (String s : splitContent) {
      if (s.isBlank()) {
        continue;
      }
      commandLine.add(s.strip());
    }

    if (commandLine.size() == 0) {
      return;
    }

    String commandName = commandLine.get(0);
    Command command = this.commandMap.getCommand(commandName);

    if (command == null) {
      return;
    }

    if (!command.allowsExecutionFrom(this.getClass())) {
      return;
    }

    List<String> arguments = new ArrayList<>();

    for (int i = 1; i < commandLine.size(); i++) {
      arguments.add(commandLine.get(i));
    }

    this.dispatcher.execute(command,new Arguments(arguments));
  }
}
