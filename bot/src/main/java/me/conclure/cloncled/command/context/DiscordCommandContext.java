package me.conclure.cloncled.command.context;

import net.dv8tion.jda.api.entities.Message;

import me.conclure.cloncled.command.Arguments;

public class DiscordCommandContext extends CommandContext {
  private final Message message;

  public DiscordCommandContext(Message message, Arguments arguments) {
    super(arguments);
    this.message = message;
  }

  public Message message() {
    return this.message;
  }
}
