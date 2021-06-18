package me.conclure.cloncled.commands.discord;

import me.conclure.cloncled.command.Command;
import me.conclure.cloncled.command.context.DiscordCommandContext;

public class PingCommand extends Command<DiscordCommandContext> {

  public PingCommand() {
    super("ping");
  }

  @Override
  protected void execute(DiscordCommandContext context) {
    context.message().getChannel().sendMessage("pong").queue();
  }
}
